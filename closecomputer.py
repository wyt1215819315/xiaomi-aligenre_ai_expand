# -*-coding:gb2312-*-
import sys

import requests
import time
import os

url = "http://127.0.0.1:8060/api/turnOffComputer/"
secret = "your secret"
times = 10
proxies = {
    'http': '127.0.0.1:7890',
    'https': '127.0.0.1:7890'
}
useproxies = False


def get_server():
    global useproxies
    try:
        if useproxies:
            r = requests.get(url + secret, proxies=proxies)
            if r.status_code != 200:
                print("远程服务器异常(use proxies)！")
                useproxies = False
                return False
            if r.text == "success":
                return True
        else:
            r = requests.get(url + secret)
            if r.status_code != 200:
                useproxies = True
                print("远程服务器异常！")
                return False
            if r.text == "success":
                return True
    except:
        print("请求远程服务器失败，请检查配置！")
        if useproxies:
            useproxies = False
        else:
            useproxies = True
        return False
    return False


print("开始监听服务端")
while True:
    if get_server():
        print("收到服务端关机指令！")
        os.system('shutdown -s -t 60')
        sys.exit()
    time.sleep(times)
