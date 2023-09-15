package com.github.prakasitnan.blogbe.api.v1;


import com.github.prakasitnan.blogbe.utils.WriteFileStream;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.io.File;

@Controller
@RequestMapping(value="/api/v1")
@CrossOrigin
public class StreamingAPI  {

	static Logger log = LogManager.getLogger(StreamingAPI.class);

	@Value("${dir.root}")
	String ROOT_DIR;
	@Value("${dir.root.project}")
	String ROOT_PROJECT_DIR;
	@RequestMapping(
			method = RequestMethod.GET,
			value = "/streaming/p/{p}/dl/{dl}"
	)
	@ResponseBody
	public void streaming(HttpServletRequest request, HttpServletResponse response,
						  @Parameter(name = "encode string", required = true) @PathVariable String p,
						  @Parameter(name = "force download file", ref="n", required = false) @PathVariable String dl
	) {
		try {
			String path = new String(Base64.decodeBase64(p));
			boolean forceDownload = false;
			if(dl != ""){
				if(dl.equalsIgnoreCase("y")){
					forceDownload = true;
				}
			}

			path = ROOT_DIR+ File.separator+ ROOT_PROJECT_DIR + File.separator + path;
			log.info("streaming path : " + path);
			// write multimedia stream file.
			if(path.toLowerCase().endsWith(".mp3") || path.toLowerCase().endsWith(".mp4")){
				WriteFileStream writeFileStream = new WriteFileStream(path);
				writeFileStream.writeStream(request, response);
			}
			// write image or other contents file.
			else{
				WriteFileStream writeFileStream = new WriteFileStream(path, forceDownload);
				writeFileStream.writeStream(request, response);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {

		}
	}
}