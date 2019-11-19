# Lambdas

This section explains how the project Lambdas were created, and presents their respective codes.

## Explanation

Because GoPiGo is connected to IoT Greengrass [as explained here](https://github.com/l-silvestre/fikalab/tree/master/Cloud), each Lambda code runs locally on GoPiGo. The Lambda code itself just creates a subprocess that executes python code that is located in the GoPiGo Desktop folder. The benefit of creating the subprocess is the convenience of changing code relative to GoPiGo movements without having to change Lambda code in the cloud, which is very difficult to do.
