def icd2do = [:].withDefault { new HashSet() }
def id = ""
new File("ont/doid.obo").eachLine { line ->
  if (line.startsWith("id:")) {
    id = line.substring(3).trim()
  }
  if (line.indexOf("ICD9") > -1) {
    def matches = line =~ /\d+\.\d+/
    matches.each { icd ->
      icd2do[icd].add(id)
    }
  }
}

Set<List> cases = new LinkedHashSet<List>()
new File("data/cases/").eachFile { file ->
  def l = null
  file.eachLine { line ->
    if (line.indexOf("CASE")>-1) {
      if (l != null) {
	cases.add(l)
      }
      l = []
    }
    def matches = line =~ /^\d+\.\d+/ 
    matches.each { 
      l << it }
  }
}

cases = cases.collect { patient ->
  patient.collect { icd2do[it] }.flatten()
}
cases.removeAll { it.size() == 0 }

cases.each { patient ->
  patient.each { doid ->
    doid = doid.replaceAll(":","_")
    print "<http://purl.obolibrary.org/obo/"+doid+"> "
  }
  println ""
}
