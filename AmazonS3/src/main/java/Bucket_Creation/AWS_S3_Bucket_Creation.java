package Bucket_Creation;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CreateBucketConfiguration;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;

public class AWS_S3_Bucket_Creation {
	
	public static void main(String args[])
	{
	
	Region region = Region.EU_WEST_2;
	S3Client s3 = S3Client.builder().region(region).build();
	String bucket = "bucketcreating-task";

	CreateBucketRequest createBucketRequest = CreateBucketRequest
	    .builder()
	    .bucket(bucket)
	    .createBucketConfiguration(CreateBucketConfiguration.builder()
	        .locationConstraint(region.id())
	        .build())
	    .build();

	s3.createBucket(createBucketRequest);

	}
}

