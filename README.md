# Java-Task_of_Week11A

Create ServerSocket in ObjectServer and start an infinite loop, so the server can only be shut down when RentManager sends it an EXIT command
ObjectServer should use ObjectInputStream and ObjectOutputStream for communicating with RentManager and handling files.
RentManager should use ObjectInputStream and ObjectOutputStream for communicating with ObjectServer
ObjectServer communicates with RentManager by sockets.
After RentManager sent GET, ObjectServer should be switched to LOAD mode, so it should load the file which contains previously stored data and should send it to client, so it will be readable in the socket's inputstream in the RentManager class
After RentManager sent PUT, ObjectServer should be switched to SAVE mode, so every object which is sent by RentManager after this moment should be stored in a previously opened file.
After RentManager sent EXIT, ObjectServer should be shut down: infinite loop should be terminated and JVM should be exited.
Person and every product class should implement java.io.Serializable
There is only 1 file needed for storing data.
If you don't have a previously stored data in a file, instantiate some objects in RentManager and send them to ObjectServer after you sent a PUT command.

![Java exercise UML](http://lms.codecool.com/File/DownloadPicture-1931d51785d2e511a08500151788bf01/Medium?downloadName=objectserver.png)
