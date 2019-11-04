package com.objectia.models;

/**
 * Mail message
 */
public class MailMessage {
	private String from;
    private String[] to;
    private String subject;
    private String text;
	
	/**
	 * Class constructor
	 * 
	 * @param status the HTTP status code
	 * @param body the HTTP text body
	 */
	public MailMessage(final String from, final String subject, final String text, final String ... to) {
        this.from = from;
        this.to = to;
		this.subject = subject;
		this.text = text;
	}

    /**
     * Gets the from email address
     * 
     * @return a string with from email address
     */
	public String getFrom() {
		return this.from;
	}

    /**
     * Gets the recipient email addresses
     * 
     * @return a string array with from recipient email addresses
     */
	public String[] getTo() {
		return this.to;
	}

    /**
     * Gets the subject
     * 
     * @return a string with message subject
     */
	public String getSubject() {
		return this.subject;
	}

    /**
     * Gets the text body
     * 
     * @return a string with the text body
     */
	public String getText() {
		return this.text;
	}


    /*public MultipartForm asFormContent() {

        try {
            MultipartForm multipart = new MultipartForm();
             
            multipart.addHeaderField("User-Agent", "CodeJava");
            multipart.addHeaderField("Test-Header", "Header-Value");
             
            multipart.addFormField("description", "Cool Pictures");
            multipart.addFormField("keywords", "Java,upload,Spring");
             
            multipart.addFilePart("fileUpload", uploadFile1);
            multipart.addFilePart("fileUpload", uploadFile2);
 
            List<String> response = multipart.finish();
             
            System.out.println("SERVER REPLIED:");
             
            for (String line : response) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }


        return null;

    }*/

}