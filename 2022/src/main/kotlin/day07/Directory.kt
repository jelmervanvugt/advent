package day07

class Directory(val name: String, val previous: Directory?, val directories: ArrayList<Directory> = ArrayList(), val files: ArrayList<File> = ArrayList(), var size: Double = 0.0)