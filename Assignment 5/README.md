# Image Processing

## Overview of the Program: 

This program allows the user to process different image types with some basic commands. The currently supported image type is .ppm. And the program allows the user to change the brightness, flipping vertically or horizontally and changing to greyscale different component ways. 



## Instruction for script using: 

#### Step 1: Taking the given script.txt file. 

```
download the script.txt to your system. 
```

#### Step 2: Edit the Configuration

<img src="C:\Users\ASUS\Desktop\Config1.png" alt="Config1" style="zoom:50%;" />

<img src="C:\Users\ASUS\Desktop\config2.png" alt="config2" style="zoom:33%;" />

```
Go to the ImageProcess File. And find the "Edit Configurations."
In the box of Program Arguments, we have few options to type in. 
1. To run the given script by entering 
	-file [The path for script.txt]
	-f [The path for script.txt]
2. To get helping information
	-help / -h
3. To run own command lines in the terminal from IDEA
	[Input Nothing, !!!!!!! Leave it blank]

Error Handling: 
Put all commands that are not included above will receive a error message. 
```

#### Step 3: Run the ImageProcess

```
Run the ImageProcess. 
1. For using the script.txt file input, it will do these commands. 
	a. Load the given ood.ppm image as the name ood stored in the library 
		(!!! The path needs to be changed to the users' path of the ood.ppm
		The given path is like this for Windows on my laptop
        C:\Users\ASUS\Desktop\ImageProcessing\Assignment4\res\ood.ppm)
    b. Brighten the ood by 30 and save as ood-brighter. 
    c. Greyscale the ood as red-component and save as ood-red. 
    d. Greyscale the ood-brighter as blue-component and save as ood-b-blue. 
    e. Greyscale the ood as green-component and save as ood-green.
    f. Greyscale the ood as luma-component and save as ood-luma.
    g. Greyscale the ood as max-component and save as ood-max.
    h. Greyscale the ood as intensity-component and save as ood-intensity.
    i. Horizontally flip the ood and save as ood-h.
    j. Vertically flip the ood and save as ood-v.
    k. Horizontally flip the ood-b-blue and save as ood--b-blue-h.
    l. save the ood-b-blue-h to a output path. 
    m. save the ood-red to a output path. 
    n. save the ood-luma to a output path. 
 2. For usingt the self typed command lines in the console or terminal, see the
 	instructions below (Functions Instructions). 
 3. For using the help, the system will return helpful messages. 
```

## Functions Instruction: 

```
IMPORTANT!!!
The naming of the [name-in-library] cannot be the same with the commands. 
Including: "load", "brighten", "save","horizontal-flip", "vertical-flip", "red", "green", "blue", "luma", "intensity", "max". 
Attempts on naming the image as the name of the commands will cause the fail of the action. !!!

1. Loading images from a system path and add it into the program library. 
load [image-path] [name-in-library]

2. Brighten the image and save it with a name into the program library. 
brighten [Amount of changing] [Image-name] [Output-Name-In-Library] 

3. Vertically flip the image and save it with a name into the program library. 
vertical-flip [Image-name] [Output-Name-In-Library]

4. Horizontally flip the image and save it with a name into the program library. 
horizontal-flip [Image-name] [Output-Name-In-Library] 

5.Use the red component to make a greyscale image and save it with a name into the program library. 
red-component [Image-name] [Output-Name-In-Library]

6.Use the green component to make a greyscale image and save it with a name into the program library. 
green-component [Image-name] [Output-Name-In-Library]

7. Use the blue component to make a greyscale image and save it with a name into the program library. 
blue-component [Image-name] [Output-Name-In-Library]

8. Use the value component to make a greyscale image and save it with a name into the program library. 
value-component [Image-name] [Output-Name-In-Library] 

9. Use the intensity component to make a greyscale image and save it with a name into the program library. 
intensity-component [Image-name] [Output-Name-In-Library]

10. Use the luma component to make a greyscale image and save it with a name into the program library. 
luma-component [Image-name] [Output-Name-In-Library]

11. Save the image with an output system path. 
save [Image-name] [Output-Path-In-System]
```



## Program Structure 

![ImagePorcessing](C:\Users\ASUS\Desktop\ImagePorcessing.png)

### ImageProcess

This is the main class that we can run this program in two different ways. 

```
1. Using a script by calling -file / -f

2. Using terminal command lines by leaving it blank. 
```

 The imageprocess takes in a model, view and controller.

```
Error handled: 
1. Invalid function call (calling things except: -help/-h, -file/-f or blank)
```



### Controller

#### ImageController 

```
This is the controller interface that contains the method of starting the whole program as process().
```

##### 	-ImageControllerImpl

​		This class is the implementation of the controller interface. It will takes in a model, a view 	

​		and a readable input (from user). Command lines will be read and executed here. 	

```
Error handled: 
1. The command fails. Every failed commands will return "Action failed" and specific information about the failuremessage rendered to the view. . 
2. Null input from the Main. Null input for constructor will throw Exception and message rendered to the view. 
3. Rendering message fails. If the rendering message fails, it will throw error. 
```



### Model 

#### storage: 

##### ImageLibrary:

This is the interface for image storage. It allows to put a model with name into the library and make copy of an existing image model in the library. 

###### 	-ImageStorage 

​	This class implements the ImageLibrary as HashMap for library and allows users to store different types of image

​	models and make a copy of the given name. 



#### image:

##### ImageModel: 

This is the interface for different images models. It allows different operations to the model and give back copies of the information about the image. 

###### 	-AbstractImageModel: 

​	This is the abstracted class that implements the ImageModel interface. The abstract class 

​	stores the image as an 2-D array of Pixel. Each pixel will represent each pixel from the image. 

​	This class allows changes on brightness, greyscale by components, flip, clone and giving back 

​	copies of some basic information. This model is compatible for different types of images. 

​	It allows the alpha channel input with the extension of pixel. 

```
Error handled: 
1. The prohibited height and width and a null pixel[][] input. 
2. Wrong input for fliping. 
```

###### 			-PPMModel

​			PPModel is specific for the PPM image. It extends from the AbstractImageModel. There is 

​			no change over the abstract class. 



### pixel

##### Pixel 

This is the interface for representing different types of pixels (3-channels, 4-channels pixels). It allows to return different value component,  set a new value for itself  and make a hard copy of itself. 

###### 	-AbstractPixelModel 

​	This is an abstract class that implements the Pixel interface. This class stores the rgb 

​	information as a arraylist, which is flexible on the length. The length is currently limited in the	abstract class as 3 since the only allowed type is ppm. Its methods allow it to make a hard

​	copy of it self (this method is abstract), make changes on the value-component. 

###### 		-EightBitPixelModel 

​		This class extends from the AbstractPixelModel and override the abstract method copy(). 

```
Error handled: 
1. The length of pixel array input of ppm file is longer than 3 intergers. 
```



### View 

#### ImageView:

This is an interface for view. It allows to change a image with name to a writable string and render messages to the output. 

###### 	-ImageViewImpl: 

​	This class implements the ImageView. This is a generalized view class that can accept different 	types of image model. It can only output the ppm now. But just by adding information about 	head lines of png or jpg file, it will be adaptable. 

```
Error handled: 
1. The library or the appendable output is null. 
2. The failure of rendering message. 
3. The failure of finding the given name image in the library. 
```







Citation for the Image used: 

Clipart Library. “Frowny Face Pictures #1269551.” *Clipart Library*, Clipart Library, clipart-				           		library.com/clipart/8ixMR94ip.htm. 

![](C:\Users\ASUS\Desktop\CS3500\ood.png)
