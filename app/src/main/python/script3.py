import numpy as np
import cv2
import matplotlib.pyplot as plt
from PIL import Image
import base64
import io
def main(x_data,y_data):

    fig =plt.figure()
    X = x_data.split(",")
    Y = y_data.split(",")

    x =[]
    y =[]

    for i in X:
        x.append(int(i))
    for i in Y:
        y.append(int(i))

    ay =fig.add_subplot(1,1,1)
    ay.plot(x,y)
    fig.canvas.draw()

    img =np.fromstring(fig.canvas.tostring_rgb(),dtype=np.uint8,sep='')
    img = img.reshape(fig.canvas.get_width_height()[::-1]+(3,))
    img = cv2.cvtColor(img,cv2.COLOR_RGB2BGR)

    pil_im = Image.fromarray(img)
    buff = io.BytesIO()
    pil_im.save(buff,format="PNG")
    img_str = base64.b64encode(buff.getvalue())
    return ""+str(img_str,'utf-8')
