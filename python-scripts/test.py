import time
from easygopigo3 import EasyGoPiGo3

# create an instance of the GoPiGo3 class.
# gpg will be the GoPiGo3 object.
gpg = EasyGoPiGo3()

print("Move the motors forward freely for 1 second.")
gpg.forward()
time.sleep(1)
gpg.stop()