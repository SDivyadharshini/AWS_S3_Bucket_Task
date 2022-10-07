package s3;

import java.io.File;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class UploadFile {

	public static void main(String[] args) {
		final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
		String bucket_name = "bucket-new-task1";
		String key_name="optisol-dance-session";
		String file_path="C:\\Users\\dhivyadharshini.s\\Downloads\\Zumba Sesh.mp4";
		
		try {
			System.out.println("Video Uploaded");
			s3.putObject(bucket_name, key_name, new File(file_path));
		}
		catch (AmazonServiceException e)
		{
			System.err.println(e.getErrorMessage());
			System.exit(1);
		}

	}

}
