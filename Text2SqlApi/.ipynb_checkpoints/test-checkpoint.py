import requests
import json

def get_completion(prompt):
    headers = {'Content-Type': 'application/json'}
    # data = {"prompt": prompt, "history": []}
    response = requests.get(url='http://0.0.0.0:8080', headers=headers)
    return response.json()

if __name__ == '__main__':
    print(get_completion('你好'))