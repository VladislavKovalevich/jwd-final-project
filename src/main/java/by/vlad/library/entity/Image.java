package by.vlad.library.entity;

import java.util.Arrays;

/**
 * {@code Image} class represent image of books cover
 * @see AbstractEntity
 */
public class Image extends AbstractEntity{
    /** image as byte array */
    private byte[] content;

    /** encoded image content */
    private String encodeImage;

    /**
     * Constructor - default
     */
    public Image() {
    }

    /**
     * Constructor with param
     * @param content - byte array
     */
    public Image(byte[] content){
        this.content = content;
    }

    /**
     * Constructor with param
     * @param id - image id
     */
    public Image(long id) {
        super(id);
    }

    /**
     * Constructor with params
     * @param imageId - image id
     * @param imageContent - image content, byte array
     */
    public Image(long imageId, byte[] imageContent) {
        super(imageId);
        this.content = imageContent;
    }

    /**
     * Getter method for {@link Image#content}
     * @return - field value {@link Image#content}
     */
    public byte[] getContent() {
        return content;
    }

    /**
     * Setter method for {@link Image#content}
     * @param content - byte array, that contains image data
     */
    public void setContent(byte[] content) {
        this.content = content;
    }

    /**
     * Getter method for {@link Image#encodeImage}
     * @return - field value {@link Image#encodeImage}
     */
    public String getEncodeImage() {
        return encodeImage;
    }

    /**
     * Setter method for {@link Image#encodeImage}
     * @param encodeImage - encoded image data, type {@link String}
     */
    public void setEncodeImage(String encodeImage) {
        this.encodeImage = encodeImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        return Arrays.equals(content, image.content);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(content);
    }
}
