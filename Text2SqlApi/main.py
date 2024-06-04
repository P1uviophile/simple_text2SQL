from typing import Union

from fastapi import FastAPI
from fastapi import FastAPI, Request
from pydantic import BaseModel
from langchain.memory import ConversationBufferMemory

# from model import CodeLlama
app = FastAPI()

class QuestionRequest(BaseModel):
    question: str

@app.post("/api/generate_sql")
async def generate_sql(request: Request, data: QuestionRequest):
    question = data.question
    
    
    sql_query = "SELECT view_name FROM scenic_area WHERE province = '河北省'"
    print(question)
    result = conversation.run(input=user_input)
    response = {
        "sql": result
    }
    
    return response


@app.get("/")
def read_root():
    return {"Hello": "World"}

if __name__ == "__main__":
    model_path = "autodl-tmp/DB-GPT-Hub/dbgpt_hub/output/CodeLlama-7b-sql-sft"
    llm = CodeLlama(model_path)
    memory = ConversationBufferMemory()
    prompt_template = PromptTemplate(
        
        template=""
    )
    conversation = ConversationChain(
        llm=llm,
        memory=memory,
        prompt=prompt_template,
        verbose=True  # 设置为 True 可以打印出链的中间状态
    )
   
    

    uvicorn.run(app='main:app', host='0.0.0.0', port=8443, reload=False)