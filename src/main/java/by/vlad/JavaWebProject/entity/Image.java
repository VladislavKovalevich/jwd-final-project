package by.vlad.JavaWebProject.entity;

import java.util.Arrays;

public class Image extends AbstractEntity{
    private byte[] content;
    private boolean isPreview;

    public Image() {
    }

    public Image(long id) {
        super(id);
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public boolean isPreview() {
        return isPreview;
    }

    public void setPreview(boolean preview) {
        isPreview = preview;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        if (isPreview != image.isPreview) return false;
        return Arrays.equals(content, image.content);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(content);
        result = 31 * result + (isPreview ? 1 : 0);
        return result;
    }
}
