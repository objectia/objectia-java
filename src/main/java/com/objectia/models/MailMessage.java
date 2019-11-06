package com.objectia.models;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream; 
import java.io.FileNotFoundException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.HttpMultipartMode;

/**
 * MailMessage model
 */
public class MailMessage {
    private ZonedDateTime date;
	private String from;
	private String fromName;
	private String replyTo;
    private String[] to;
    private List<String> cc;
    private List<String> bcc;
    private String subject;
    private String text;
    private String html;
    private List<String> attachments;
    private List<String> tags;
    private String encoding;
    private String charset;
    private Boolean requireTLS;
    private Boolean verifyCertificate;
    private Boolean openTracking;
    private Boolean clickTracking;
    private Boolean htmlOnlyClickTracking;
    private Boolean unsubscribeTracking;
    private Boolean testMode;

	/**
	 * Class constructor
	 * 
	 * @param from the from email address
	 * @param subject the message subject
	 * @param text the message plain text body
	 * @param to the list of recipient
	 */
	public MailMessage(final String from, final String subject, final String text, final String ... to) {
        this.from = from;
        this.to = to;
		this.subject = subject;
        this.text = text;

        this.cc = new ArrayList<>();
        this.bcc = new ArrayList<>();
        this.tags = new ArrayList<>();
        this.attachments = new ArrayList<>();
    }

    /**
     * Sets the message date. The current date/time is used by default.
     * 
	 * @param date the message date.
     */
    public void setDate(final ZonedDateTime date) {
        this.date = date;
    }

    /**
     * Gets the message date.
     * 
     * @return a the message date.
     */
	public ZonedDateTime getDate() {
		return this.date;
	}

    /**
     * Sets the from email address
     * 
	 * @param from sets the from email address.
     */
    public void setFrom(final String from) {
        this.from = from;
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
     * Sets the reply to email address
     * 
	 * @param replyTo sets the reply to email address.
     */
    public void setReplyTo(final String replyTo) {
        this.replyTo = replyTo;
    }

    /**
     * Gets the reply to email address
     * 
     * @return a string with reply to email address
     */
	public String getReplyTo() {
		return this.replyTo;
	}

    /**
     * Gets the recipient email addresses
     * 
     * @return a string array with from recipient email addresses
     */
	public String[] getTo() {
		return this.to;
	}

    public void addCc(final String ...cc) {
        this.cc.addAll(Arrays.asList(cc));
    }

    public void addBcc(final String ...bcc) {
        this.bcc.addAll(Arrays.asList(bcc));
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

   /**
     * Sets the HTML body of the message
     * 
	 * @param html the HTML body of the message
     */
    public void setHTML(final String html) {
        this.html = html;
    }

   /**
     * Adds a file attachment
     * 
	 * @param fileName the file to add.
     */
    public void addAttachment(final String fileName) {
        if (this.attachments.size() < 10) {
            this.attachments.add(fileName);
        }
    }

   /**
     * Adds a tag to the message
     * 
	 * @param replyTo the tag to add
     */
    public void addTag(final String tag) {
        if (this.tags.size() < 3) {
            this.tags.add(tag);
        }
    }

   /**
     * Sets the message encoding type
     * 
	 * @param encoding the message encoding type
     */
    public void setEncoding(final String encoding) {
        this.encoding = encoding;
    }

   /**
     * Gets the message encoding
     * 
     * @return a string with message encoding type
     */
	public String getEncoding() {
		return this.encoding;
	}

   /**
     * Sets the message char set
     * 
	 * @param charset the the message char set.
     */
    public void setCharset(final String charset) {
        this.charset = charset;
    }

   /**
     * Gets the the message char set
     * 
     * @return a string with message char set
     */
    public String getCharset() {
		return this.charset;
	}

   /**
     * Enables/disables if it required to use TLS.
     * 
	 * @param flag turns the feature on or off.
     */
    public void setRequireTLS(final Boolean flag) {
        this.requireTLS = flag;
    }

   /**
     * Enables/disables if it required to verify certificates
     * 
	 * @param flag turns the feature on or off.
     */
    public void setVerifyCertificate(final Boolean flag) {
        this.verifyCertificate = flag;
    }

   /**
     * Enables/disables open tracking
     * 
	 * @param flag turns the feature on or off.
     */
    public void setOpenTracking(final Boolean flag) {
        this.openTracking = flag;
    }

   /**
     * Enables/disables click tracking
     * 
	 * @param flag turns the feature on or off.
     */
    public void setClickTracking(final Boolean flag) {
        this.clickTracking = flag;
    }

   /**
     * Enables/disables HTML only click tracking
     * 
	 * @param flag turns the feature on or off.
     */
    public void setHtmlOnlyClickTracking(final Boolean flag) {
        this.htmlOnlyClickTracking = flag;
    }

   /**
     * Enables/disables unsubscribe tracking
     * 
	 * @param flag turns the feature on or off.
     */
    public void setUnsubscribeTracking(final Boolean flag) {
        this.unsubscribeTracking = flag;
    }

   /**
     * Enables/disables test mode
     * 
	 * @param flag turns the feature on or off.
     */
    public void setTestMode(final Boolean flag) {
        this.testMode = flag;
    }

    public HttpEntity asFormContent() {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

        if (this.date != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
            System.out.println(this.date.format(formatter));
            builder.addTextBody("date", this.date.format(formatter), ContentType.TEXT_PLAIN);
        }

        builder.addTextBody("from", this.from, ContentType.TEXT_PLAIN);
        if (this.fromName != null) {
            builder.addTextBody("from_name", this.fromName, ContentType.TEXT_PLAIN);
        }

        if (this.replyTo != null) {
            builder.addTextBody("reply_to", this.replyTo, ContentType.TEXT_PLAIN);
        }

        for (String s : this.to) {
            builder.addTextBody("to", s, ContentType.TEXT_PLAIN);
        }
        for (String s : this.cc) {
            builder.addTextBody("cc", s, ContentType.TEXT_PLAIN);
        }
        for (String s : this.bcc) {
            builder.addTextBody("bcc", s, ContentType.TEXT_PLAIN);
        }

        builder.addTextBody("subject", this.subject, ContentType.TEXT_PLAIN);
        builder.addTextBody("text", this.text, ContentType.TEXT_PLAIN);

        if (this.html != null) {
            builder.addTextBody("html", this.text, ContentType.TEXT_HTML);
        }

        for (String tag : this.tags) {
            builder.addTextBody("tags", tag, ContentType.TEXT_PLAIN);
        }

        if (this.encoding != null) {
            builder.addTextBody("encoding", this.encoding, ContentType.TEXT_PLAIN);
        }
        if (this.charset != null) {
            builder.addTextBody("charset", this.charset, ContentType.TEXT_PLAIN);
        }

        if (this.requireTLS != null) {
            builder.addTextBody("require_tls", this.requireTLS.toString(), ContentType.TEXT_PLAIN);
        }
        if (this.verifyCertificate != null) {
            builder.addTextBody("verify_cert", this.verifyCertificate.toString(), ContentType.TEXT_PLAIN);
        }
        if (this.openTracking != null) {
            builder.addTextBody("open_tracking", this.openTracking.toString(), ContentType.TEXT_PLAIN);
        }
        if (this.clickTracking != null) {
            builder.addTextBody("click_tracking", this.clickTracking.toString(), ContentType.TEXT_PLAIN);
        }
        if (this.htmlOnlyClickTracking != null) {
            builder.addTextBody("html_click_tracking", this.htmlOnlyClickTracking.toString(), ContentType.TEXT_PLAIN);
        }
        if (this.unsubscribeTracking != null) {
            builder.addTextBody("unsubscribe_tracking", this.unsubscribeTracking.toString(), ContentType.TEXT_PLAIN);
        }
        if (this.testMode != null) {
            builder.addTextBody("test_mode", this.testMode.toString(), ContentType.TEXT_PLAIN);
        }

        for (String fn : this.attachments) {
            try {
                File f = new File(fn);    
                builder.addBinaryBody(
                    f.getName(),
                    new FileInputStream(f),
                    ContentType.APPLICATION_OCTET_STREAM,
                    f.getName()
                );
            } catch (FileNotFoundException  ex) {
                // Log...
            }
        }

        return builder.build();        
    }
}