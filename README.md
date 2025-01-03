# FileFly
  
Basic file server / client pair of programs. 
  
Once setup, the user can send / request files from the server which stores them on disk.
  
The user can also list all files currently stored by the server.

This project was made to learn about socket programming and network protocols.
  
## Basic usage

List all files in remote file server
```console
foo@bar:~$ filefly list
```

Send a specified file to the file server
```console
foo@bar:~$ filefly send filename
```

Pull a file from the file server
```console
foo@bar:~$ filefly ask filename
```

