def map = [:].withDefault { new LinkedHashSet() }
new File(args[0]).splitEachLine("\t") { line ->
  def id = line[0]
  def p = line[1]
  map[id].add(p)
}
map.each { id, ps ->
  ps.each { p ->
    if (p) {
      p = p.replaceAll(":","_")
      p = "<http://purl.obolibrary.org/obo/"+p+">"
      print "$p "
    }
  }
  println ""
}