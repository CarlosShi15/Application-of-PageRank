public void Iteration(Map<String, Canteenitem> teamMaps, int
max) {
 Map<String, Canteenitem> tmp = new HashMap<String,Canteenitem>();
 double totalPageRank = 0.0; //
PageRank
 double dampingFactor = 0.85; //
 int N = teamMaps.size(); //

 for (Canteenitem canteenitem : teamMaps.values()) {
 Canteenitem tmpTeam = new Canteenitem();
 tmpTeam.setName(canteenitem.getName());
 double pagerank = 0;
 int count = 0;

 for (MatchResult matchResult : this.matchResultList) {
 int weight = matchResult.getWeight(canteenitem.getName());
 if (weight != -1) {
 Canteenitem otherCanteen =
teamMaps.get(matchResult.getOtherTeam(canteenitem.getName()));
 if (otherCanteen != null) {
 double pr = otherCanteen.getPagerank();
 pagerank += weight * pr;
 count++;
 }
 }
 }

 // PageRank
 double newPageRank = (1 - dampingFactor) / N +
dampingFactor * (pagerank / count);
 tmpTeam.setPagerank(newPageRank);
 totalPageRank += newPageRank;
 tmp.put(tmpTeam.getName(), tmpTeam);
 }

 // PageRank , 1
for (Canteenitem canteenitem : tmp.values()) {
canteenitem.setPagerank(canteenitem.getPagerank() /
totalPageRank);
 }

 max--;
 this.canteenmap = tmp;
 if (max > 0) {
 Iteration(this.canteenmap, max);
 }
 }

 import com.pagerank.core.PageRank;
 public class Test {
 @org.junit.Test
 public void test(){
 String Canteen = "canteen_list.json";
 String match = "canteen_result_list";
 int max = 500;
 PageRank pageRank = new PageRank(Canteen, match, max);
 pageRank.Iteration(pageRank.getCanteenmap(),
pageRank.getMax());
 pageRank.wirteToFile();
 pageRank.print();
 }
 }