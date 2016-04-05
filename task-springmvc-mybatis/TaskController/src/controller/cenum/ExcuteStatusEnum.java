package controller.cenum;

public enum ExcuteStatusEnum {

	Excuting("0","执行中"), End("1","完成");
	
	private final String code;
	
	private final String name;

	private ExcuteStatusEnum(String code,String name) {
		this.code = code;
		this.name=name;
	}

	public static String resStatus(int i) {
		String result="";
		switch (i) {
		case 0:
			result = ExcuteStatusEnum.Excuting.getName();
			break;
		case 1:
			result = ExcuteStatusEnum.End.getName();
			break;
		}
		return result;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}
}
