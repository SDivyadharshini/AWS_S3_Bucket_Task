package s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;

public class create_bucket {

	public static void main(String[] args) {
		final AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
		String bucket_name = "bucket-new-task1";
		
		try
		{
			System.out.println("Bucket Created");
			s3.createBucket(bucket_name);
		}
		catch(AmazonS3Exception e)
		{
			System.err.println(e.getErrorMessage());
		}

	}

}
