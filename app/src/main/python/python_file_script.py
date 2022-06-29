import os
import io
from os.path import join,dirname
def main(data):
    filname = join(dirname(__file__),'readme.txt')
    with open(filname, 'w',encoding='utf8') as f:
        f.write(data)
        f.close()

    with open(filname, 'r',encoding='utf8') as f:
        strs = f.read()
    return ""+strs
