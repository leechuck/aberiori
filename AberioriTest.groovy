import de.leechuck.aberiori.*
import de.leechuck.aberiori.rules.*


def a = new Aberiori(args[0])
def itemsets = a.runAlgorithm(0.1, "data/cases/cases-processes.txt", null)
def b = new AberioriRules(args[0])
def rules = b.runAlgorithm(itemsets, null, 232, 0.4, 2)
rules.sortByConfidence()
Map int2class = a.getInt2Class()

def fout = new PrintWriter(new BufferedWriter(new FileWriter("data/cases/cases.out")))

rules.getRules().each { rule ->
  def itemset1 = rule.getItemset1()
  def itemset2 = rule.getItemset2()
  fout.print(rule.getConfidence()+" ")
  fout.print(rule.getLift()+" ")
  for (int i = 0 ; i < itemset1.size() ; i++) {
    def item = itemset1.get(i)
    fout.print(int2class[item]+" ")
  }
  fout.print(" ===> ")
  for (int i = 0 ; i < itemset2.size() ; i++) {
    def item = itemset2.get(i)
    fout.print(int2class[item]+" ")
  }
  fout.println("")
}

/*
List levels = itemsets.getLevels()
levels.each { level ->
  level.each { itemset ->
    for (int i = 0 ; i < itemset.size() ; i++) {
      def item = itemset.get(i)
      fout.print(int2class[item]+" ")
    }
    fout.println(itemset.getAbsoluteSupport())
  }
  fout.println("===================================================================================================")
}
*/

fout.flush()
fout.close()
