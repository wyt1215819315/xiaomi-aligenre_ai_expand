# -*-coding:gb2312-*-
import sys

import requests
import time
import os

url = "http://127.0.0.1:8060/api/turnOffComputer/"
secret = "your secret"
times = 10


def get_server():
    try:
        r = requests.get(url + secret)
        if r.status_code != 200:
            print("Զ�̷������쳣��")
            return False
        if r.text == "success":
            return True
    except:
        print("����Զ�̷�����ʧ�ܣ��������ã�")
        return False
    return False


print("��ʼ���������")
while True:
    if get_server():
        print("�յ�����˹ػ�ָ�")
        os.system('shutdown -s -t 100')
        sys.exit()
    time.sleep(times)
