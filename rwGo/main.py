import json
import os
import time

import numpy as np
from matplotlib import use as mpl_use
import matplotlib.pyplot as plt

if __name__ == '__main__':
    with open("output.json", "r") as my_file:
        experiments = my_file.read()

    exp = json.loads(experiments)

    neurons = {}
    iters = {}
    prec = []
    nowNeurons = 0
    line = []
    for el in exp["Experiments"]:
        if el["Neurons"] != nowNeurons and line != []:
            prec.append(line)
            line = []
        nowNeurons = el["Neurons"]
        line.append(el["Accuracy"])
        neurons[el["Neurons"]] = True
        iters[el["Iterations"]] = True
        # while
        # neurons.append(el["Neurons"])
        # iters.append(el["Iterations"])
        # prec.append(el["Accuracy"])

    plt.figure()
    plt.imshow(prec, cmap='rainbow', aspect='equal', vmin=0, vmax=100, origin="lower")
    plt.colorbar()
    plt.yticks(ticks=np.arange(20), labels=neurons.keys())
    plt.xticks(ticks=np.arange(6), labels=iters.keys(), rotation=90)
    plt.show()
    # time.sleep(10)
