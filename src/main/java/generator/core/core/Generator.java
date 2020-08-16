package generator.core.core;

import java.util.ArrayList;
import java.util.List;

import generator.core.core.exec.GenerateExcutor;
import generator.core.utils.DBUtil;
import lombok.Data;

@Data
public class Generator {
	
	private List<GenerateExcutor> excutors;

	public Generator() {
		excutors = new ArrayList<>();
	}
	
	public void addExcutor(GenerateExcutor excutor) {
		this.excutors.add(excutor);
	}
	
	public void run() throws Exception {
		for(GenerateExcutor excutor : excutors) {
			excutor.generate();
		}
		DBUtil.close();
	}
	
	
	
}
