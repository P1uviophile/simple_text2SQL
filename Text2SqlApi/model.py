from typing import Optional, List, Any
from langchain.llms.base import LLM
from transformers import AutoTokenizer, AutoModelForCausalLM
import torch
from transformers import GenerationConfig
from safetensors import safe_open

class CodeLlamaSqlLLM(LLM):
    tokenizer: AutoTokenizer = None
    model: AutoModelForCausalLM = None

    def __init__(self, model_path: str):
        # model_path: CodeLlama模型路径
        # 从本地初始化模型
        super().__init__()
        print("正在从本地加载模型...")
        self.tokenizer = AutoTokenizer.from_pretrained(model_path, trust_remote_code=True)
        self.model = AutoModelForCausalLM.from_pretrained(model_path, trust_remote_code=True, torch_dtype=torch.bfloat16, device_map="auto")

        # 使用SafeTensors加载模型权重
        with safe_open(f"{model_path}/model-00001-of-00002.safetensors", framework="pt", device="gpu"):
            self.model.load_state_dict(safe_tensors["pytorch_model.bin"], strict=False)

        with safe_open(f"{model_path}/model-00002-of-00002.safetensors", framework="pt", device="gpu"):
            self.model.load_state_dict(safe_tensors["pytorch_model.bin"], strict=False)

        self.model.generation_config = GenerationConfig.from_pretrained(model_path)
        self.model = self.model.eval()
        print("完成本地模型的加载")

    def _call(self, prompt: str, stop: Optional[List[str]] = None, run_manager: Optional[CallbackManagerForLLMRun] = None, **kwargs: Any):
        # 重写调用函数
        inputs = self.tokenizer(prompt, return_tensors="pt")
        output_ids = self.model.generate(**inputs, max_new_tokens=1024, pad_token_id=self.tokenizer.eos_token_id)
        response = self.tokenizer.batch_decode(output_ids, skip_special_tokens=True, clean_up_tokenization_spaces=True)[0]
        return response

    @property
    def _llm_type(self) -> str:
        return "codellama_sql_llm"