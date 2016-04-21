package taskweb.action;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import model.User;

@Controller
public class JsonController {
	@RequestMapping("/jsonuser")
	public @ResponseBody User execute() throws Exception {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getResponse();
		User user = new User();
		String strImg = getImageStr("D:\\DSC_0958.JPG");
		generateImage(strImg,"E:\\123.JPG");
		user.setUserName(strImg);
		return user;
	}

	public String getImageStr(String imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		// String imgFile = "d:\\111.jpg";// 待处理的图片
		InputStream in = null;
		byte[] data = null;
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}

	public boolean generateImage(String imgStr, String imgFile)
			throws Exception {// 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			// 生成jpeg图片
			String imgFilePath = imgFile;// 新生成的图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			throw e;
		}
	}
}
