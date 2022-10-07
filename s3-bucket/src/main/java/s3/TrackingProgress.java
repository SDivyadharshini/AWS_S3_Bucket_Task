package s3;

import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;



import java.io.File;
import java.text.DecimalFormat;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.event.ProgressEvent;
import com.amazonaws.event.ProgressListener;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class TrackingProgress {
	
	public static long AllBytesTransferred = 0;
	private static final DecimalFormat df = new DecimalFormat("0.00");
	
public static void main(String args[])
{
String bucketName = "bucket-new-task1";
String keyName ="optisol-dance-session";
String filePath = "C:\\\\Users\\\\dhivyadharshini.s\\\\Downloads\\\\Zumba Sesh.mp4";
TransferManager tm = null;

try
{
AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard().withCredentials(new DefaultAWSCredentialsProviderChain()).withRegion(Regions.EU_WEST_2).build();

tm = TransferManagerBuilder.standard().withS3Client(amazonS3).withMultipartUploadThreshold((long) (5 * 1024 * 1025)).build();

File file = new File(filePath);
final long totalBytes = file.length();

System.out.println("Uploading Video to S3 Bucket...");
Upload upload = tm.upload(bucketName, keyName, file);

upload.addProgressListener(new ProgressListener() {
public void progressChanged(ProgressEvent progressEvent) {
long BytesUploaded = progressEvent.getBytesTransferred();
AllBytesTransferred = AllBytesTransferred + BytesUploaded;
double temp = (double)AllBytesTransferred/totalBytes;
System.out.println(" Video Uploading percentage :: " + df.format(temp*100) + "%");
}
});

upload.waitForCompletion();
if(upload.isDone()) {
tm.shutdownNow();
System.out.println("Video Uploaded successfully!!");
}
}
catch (Exception e) {
System.out.println(e.getMessage());
System.out.println("File Upload Failed");
tm.shutdownNow();
}
}
}
