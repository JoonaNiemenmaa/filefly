# FileFly
  
Basic file server / client pair of programs. You use fileflyclient through the console to send files over to a fileflyserver process which stores them on disk.
  
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

