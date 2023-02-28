package Day20.gallery.model.Dao;

public class MemberDao extends Dao{

	// 1. 싱글톤
	private static MemberDao dao = new MemberDao();
	private MemberDao() { }
	public static MemberDao getInstance() { return dao; }
}
