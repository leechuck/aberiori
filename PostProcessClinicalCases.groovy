def id = ""
def id2name = [:]
new File("ont/doid.obo").eachLine { line ->
  if (line.startsWith("id:")) {
    id = line.substring(3).trim()
  }
  if (line.startsWith("name:")) {
    def name = line.substring(5).trim()
    id2name[id] = name
  }
}


new File("data/cases/cases.out").splitEachLine(" ") { line ->
  line.each { i ->
    i = i.replaceAll("<http://purl.obolibrary.org/obo/","").replaceAll("_",":").replaceAll(">","").replaceAll("===","===>")
    if (id2name[i]) {
      print id2name[i]+", "
    } else {
      print i+", "
    }
  }
  println ""
}