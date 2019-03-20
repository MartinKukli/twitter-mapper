package ui;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;
import org.openstreetmap.gui.jmapviewer.MapMarkerCircle;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MapMarkerCirclePlus extends MapMarkerCircle {
    private BufferedImage miniImage;
    private String userProfileImageURL;
    private String tweet;

    public MapMarkerCirclePlus(Layer layer, Coordinate coord, Color color, BufferedImage miniImage,
                               String userProfileImageURL, String tweet) {
        super(layer, null, coord, 40.0, STYLE.FIXED, getDefaultStyle());
        this.miniImage = miniImage;
        this.userProfileImageURL = userProfileImageURL;
        this.tweet = tweet;

        setColor(Color.BLACK);
        setBackColor(color);
    }

    // getters
    public String getUserProfileImageURL() {
        return userProfileImageURL;
    }
    public String getTweet() {
        return tweet;
    }

    @Override
    public void paint(Graphics g, Point position, int radius) {
        int size = radius;
        int imgSize = radius - 20;
        if (g instanceof Graphics2D && this.getBackColor() != null) {
            Graphics2D g2 = (Graphics2D)g;
            Composite oldComposite = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(3));
            g2.setPaint(this.getBackColor());
            g.fillOval(position.x - radius, position.y - radius, size, size);
            g.drawImage(miniImage, position.x - 30, position.y - 30, imgSize, imgSize,
                    Color.black , null);
            g2.setComposite(oldComposite);
        }
        g.setColor(this.getColor());
        g.drawOval(position.x - radius, position.y - radius, size, size);

        if (this.getLayer() == null || this.getLayer().isVisibleTexts()) {
            this.paintText(g, position);
        }
    }
}
