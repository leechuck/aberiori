import de.leechuck.aberiori.*

def a = new Aberiori("ont/plant_ontology.obo")
def itemsets = a.runAlgorithm(0.1, "testfile.aber", null)

Map int2class = a.getInt2Class()
List levels = itemsets.getLevels()
levels.each { level ->
  level.each { itemset ->
    for (int i = 0 ; i < itemset.size() ; i++) {
      def item = itemset.get(i)
      print int2class[item]+" "
    }
    println ""
  }
  println "=================================="
}
