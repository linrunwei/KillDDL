# KillDDL - Your Productivity Booster

## I. Team Members
|Name|Email|USC ID|
|-|-|-|
|Wanchen Jiang|wanchenj@usc.edu|6729012501| 
|Runwei Lin|runweili@usc.edu|5191117577|
|Jinpeng He|jinpengh@usc.edu|9949408107|
|Hao Chen|chen579@usc.edu|2286507011|
|Yanxi Li|yanxili@usc.edu|3875675944|

## II. Preface:  
KillDDL is an Android project. It is an application that helps users organize their tasks with ease. We are making it an efficiency booster by allowing users to choose different frequencies of push notification reminder, set the pattern of repetition of a task, and select type and priority of a task. We are making this application simple and straightforward, so users can better focus on organizing their work and achieve more.

## III. Introduction:  
We often have a headache when there are tons of work to do but we sadly find ourselves lost in a mess of tasks with different deadlines and priorities. Sometimes we managed to find the right thing to do after cleaning up the threads our mind. But more often, we suffer a lot because we miss a deadline, and eventually costs us even more to fix. As a result, many people tried using deadline management tools, but results tend to mix, either because users are overwhelmed by the complexity of the software, or because the software is incompetent in doing what the users expect. Here comes KillDDL, our deadline killer and efficiency booster. KillDDL empower the users in many ways. Apart from basic functions that a deadline management tool have, we also introduce some new features. For example, it allows users to choose varying frequencies of push notification reminder, so that the user can either set a single notification for an incoming due, or set a series of notifications each day before the due. User can also set the pattern of repetition of a task, so the user will be notified of recurring tasks, like monthly rent payments. Moreover, the user can select type and priority of a task so that the user can better focus on the things of a certain type or level of importance.

Similar applications in market includes Things, Wunderlist, Omnifocus, etc. These applications are good as testified by their popularity among users. Still, users often find themselves lost when they try to learn how to best utilize them; the cost of learning is often very high for those who just want to make use of a productivity tool. We believe that the lower cost of learning is important for better user experience. Also, we would like to help user focus on their tasks instead of managing and organizing them. So we are making this application to minimize the overhead of learning and provide users the most simple and straightforward experiences.

## IV. Notes for Test Run Our Application:  
1. Download latest version of Android Studio;  
2. Download our project and unzip it;  
3. Open up Android Studio, select `Open an existing Android Studio project`, navigate to the unzipped project folder and select `Open`;  
4. The project will start to build automatically, wait until it finishes. It should take less than a minute;  
5. Click `Run` button on the toolbar on the top;  
6. When prompted for selecting the deployment target, click `Create a New Virtual Device`;  
7. Navigating through the device list, select `Nexus 5` and click `Next`;  
8. Under system image, select `Oreo`, which should show an API level of `26`. You might need to download it first if you have not. Once finished, click `Next`;
9. You might change the `AVD Name` here if you want. Once done, click `Finish`;  
10. Select the virtual device you just added under `Available Virtual Device`;  
11. Wait until the system finish booting up. It should take less than a minute;  
12. Once loaded, our application will automatically show up on the virtual device. Please enjoy!

## V. Spring #1 Improvements  
1. Overhaul `Menu` from scratch. Now `Task` items in `menu` now support drag and drop to re-order. It also shows due date and time for each `Task` item. Added a color dot, which is selected by user when a `Task` is created by user, for each `Task` item.
2. Add `Forgot Password` functionality that allows user to reset his or her password if forgot.
3. Add functionalities to add user's profile picture and display it in the user's `Profile`.
4. Add `Delete` button that allows user to delete a task when editing a `Task`.
5. Add the ability to change between Light theme and Dark theme.
6. Add the ability to edit user's password.
7. Remove deprecated files and refactor code.