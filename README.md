
Project Description: Load images from the subreddit /r/images and open the image in full-screen when clicked on any image.

Feature detail - 
--> It will show all the images in the "RecyclerView" from the response JSON.
--> When any image is clicked, it'll open its preview mode(full screen).
--> Handled "No Internet connection" scenario.

Decisions - 
--> I used image source URL, first. Then, to optimise it due to the high-resolution images, I used the best scaled-down resolution provided by the JSON, for each image.

Assumptions made while developing the library - 
--> Bacause, I had to load Image from network, I used Imageloader and volley library.
--> Helper that handles loading and caching images from remote URLs.
