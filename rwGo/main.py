import os
from matplotlib import use as mpl_use
mpl_use('Qt5Agg')
import matplotlib.pyplot as plt

if __name__ == '__main__':
    x = []
    y = []
    z = []
    for n in range(10):
        for iters in range(10):
            x.append(n + 1)
            y.append((iters + 1) * 10000)
            p1 = os.system("..\\rwGo\\neuro.exe " + str(n) + " " + str(iters))
            p2 = os.system("..\\rwGo\\neuro.exe " + str(n) + " " + str(iters))
            p3 = os.system("..\\rwGo\\neuro.exe " + str(n) + " " + str(iters))
            z.append((p1 + p2 + p3) / 3)
    fig = plt.figure(figsize=(10, 10))
    ax = fig.add_subplot(111, projection='3d')
    ax.view_init(100, 110, 135)
    ax.scatter(x, y, z, s=5, c=z, cmap='coolwarm')
