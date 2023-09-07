public class Main {
	public static void main(String[] args) {
		MemberDB mdb = new MemberDB();
		System.out.print("Find All: \n");
		mdb.FindAll().DisplayData();
		System.out.print("\n");
		System.out.print("Find One: \n");
		mdb.FindOne("baramee").DisplayData();
	}
}
