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

    public Image() {
    }

    public Image(byte[] content){
        this.content = content;
    }

    public Image(long id) {
        super(id);
    }

    public Image(long imageId, byte[] imageContent) {
        super(imageId);
        this.content = imageContent;
    }
    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getEncodeImage() {
        return encodeImage;
    }

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
