//package com.esotericsoftware.spine.attachments;
//
//import static com.badlogic.gdx.graphics.g2d.Batch.*;
//
//import com.badlogic.gdx.graphics.Color;
//import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
//import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.math.MathUtils;
//import com.badlogic.gdx.utils.NumberUtils;
//import com.esotericsoftware.spine.Bone;
//import com.esotericsoftware.spine.Skeleton;
//import com.esotericsoftware.spine.Slot;
//import com.esotericsoftware.spine.attachments.Attachment;
//import com.esotericsoftware.spine.attachments.RegionAttachment;
//
///** An attachment that displays a textured quadrilateral.
// * <p>
// * See <a href="http://esotericsoftware.com/spine-regions">Region attachments</a> in the Spine User Guide. */
////public class RegionAttachment35  {
//    static public final int BLX = 0;
//    static public final int BLY = 1;
//    static public final int ULX = 2;
//    static public final int ULY = 3;
//    static public final int URX = 4;
//    static public final int URY = 5;
//    static public final int BRX = 6;
//    static public final int BRY = 7;
//
//    private TextureRegion region;
//    private String path;
//    private float x, y, scaleX = 1, scaleY = 1, rotation, width, height;
//    private final float[] vertices = new float[20];
//    private final float[] offset = new float[8];
//    private final Color color = new Color(1, 1, 1, 1);
//
//    public RegionAttachment35 (String name) {
//        super(name);
//    }
//
//    /** Calculates the {@link #offset} using the region settings. Must be called after changing region settings. */
//    public void updateOffset () {
//        float width = getWidth();
//        float height = getHeight();
//        float localX2 = width / 2;
//        float localY2 = height / 2;
//        float localX = -localX2;
//        float localY = -localY2;
//        if (region instanceof AtlasRegion) {
//            AtlasRegion region = (AtlasRegion)this.region;
//            if (region.rotate) {
//                localX += region.offsetX / region.originalWidth * width;
//                localY += region.offsetY / region.originalHeight * height;
//                localX2 -= (region.originalWidth - region.offsetX - region.packedHeight) / region.originalWidth * width;
//                localY2 -= (region.originalHeight - region.offsetY - region.packedWidth) / region.originalHeight * height;
//            } else {
//                localX += region.offsetX / region.originalWidth * width;
//                localY += region.offsetY / region.originalHeight * height;
//                localX2 -= (region.originalWidth - region.offsetX - region.packedWidth) / region.originalWidth * width;
//                localY2 -= (region.originalHeight - region.offsetY - region.packedHeight) / region.originalHeight * height;
//            }
//        }
//        float scaleX = getScaleX();
//        float scaleY = getScaleY();
//        localX *= scaleX;
//        localY *= scaleY;
//        localX2 *= scaleX;
//        localY2 *= scaleY;
//        float rotation = getRotation();
//        float cos = MathUtils.cosDeg(rotation);
//        float sin = MathUtils.sinDeg(rotation);
//        float x = getX();
//        float y = getY();
//        float localXCos = localX * cos + x;
//        float localXSin = localX * sin;
//        float localYCos = localY * cos + y;
//        float localYSin = localY * sin;
//        float localX2Cos = localX2 * cos + x;
//        float localX2Sin = localX2 * sin;
//        float localY2Cos = localY2 * cos + y;
//        float localY2Sin = localY2 * sin;
//        float[] offset = this.offset;
//        offset[BLX] = localXCos - localYSin;
//        offset[BLY] = localYCos + localXSin;
//        offset[ULX] = localXCos - localY2Sin;
//        offset[ULY] = localY2Cos + localXSin;
//        offset[URX] = localX2Cos - localY2Sin;
//        offset[URY] = localY2Cos + localX2Sin;
//        offset[BRX] = localX2Cos - localYSin;
//        offset[BRY] = localYCos + localX2Sin;
//    }
//
//    public void setRegion (TextureRegion region) {
//        if (region == null) throw new IllegalArgumentException("region cannot be null.");
//        this.region = region;
//        float[] vertices = this.vertices;
//        if (region instanceof AtlasRegion && ((AtlasRegion)region).rotate) {
//            vertices[U3] = region.getU();
//            vertices[V3] = region.getV2();
//            vertices[U4] = region.getU();
//            vertices[V4] = region.getV();
//            vertices[U1] = region.getU2();
//            vertices[V1] = region.getV();
//            vertices[U2] = region.getU2();
//            vertices[V2] = region.getV2();
//        } else {
//            vertices[U2] = region.getU();
//            vertices[V2] = region.getV2();
//            vertices[U3] = region.getU();
//            vertices[V3] = region.getV();
//            vertices[U4] = region.getU2();
//            vertices[V4] = region.getV();
//            vertices[U1] = region.getU2();
//            vertices[V1] = region.getV2();
//        }
//    }
//
//    public TextureRegion getRegion () {
//        if (region == null) throw new IllegalStateException("Region has not been set: " + this);
//        return region;
//    }
//
//    /** @return The updated world vertices. */
//    public float[] updateWorldVertices (Slot slot, boolean premultipliedAlpha) {
//        Skeleton skeleton = slot.getSkeleton();
//        Color skeletonColor = skeleton.getColor();
//        Color slotColor = slot.getColor();
//        Color regionColor = color;
//        float alpha = skeletonColor.a * slotColor.a * regionColor.a * 255;
//        float multiplier = premultipliedAlpha ? alpha : 255;
//        float color = NumberUtils.intToFloatColor( //
//                ((int)alpha << 24) //
//                        | ((int)(skeletonColor.b * slotColor.b * regionColor.b * multiplier) << 16) //
//                        | ((int)(skeletonColor.g * slotColor.g * regionColor.g * multiplier) << 8) //
//                        | (int)(skeletonColor.r * slotColor.r * regionColor.r * multiplier));
//
//        float[] vertices = this.vertices;
//        float[] offset = this.offset;
//        Bone bone = slot.getBone();
//        float x = bone.getWorldX(), y = bone.getWorldY();
//        float a = bone.getA(), b = bone.getB(), c = bone.getC(), d = bone.getD();
//        float offsetX, offsetY;
//
//        offsetX = offset[BRX];
//        offsetY = offset[BRY];
//        vertices[X1] = offsetX * a + offsetY * b + x; // br
//        vertices[Y1] = offsetX * c + offsetY * d + y;
//        vertices[C1] = color;
//
//        offsetX = offset[BLX];
//        offsetY = offset[BLY];
//        vertices[X2] = offsetX * a + offsetY * b + x; // bl
//        vertices[Y2] = offsetX * c + offsetY * d + y;
//        vertices[C2] = color;
//
//        offsetX = offset[ULX];
//        offsetY = offset[ULY];
//        vertices[X3] = offsetX * a + offsetY * b + x; // ul
//        vertices[Y3] = offsetX * c + offsetY * d + y;
//        vertices[C3] = color;
//
//        offsetX = offset[URX];
//        offsetY = offset[URY];
//        vertices[X4] = offsetX * a + offsetY * b + x; // ur
//        vertices[Y4] = offsetX * c + offsetY * d + y;
//        vertices[C4] = color;
//        return vertices;
//    }
//
//    public float[] getWorldVertices () {
//        return vertices;
//    }
//
//    /** For each of the 4 vertices, a pair of <code>x,y</code> values that is the local position of the vertex.
//     * <p>
//     * See {@link #updateOffset()}. */
//    public float[] getOffset () {
//        return offset;
//    }
//
//    /** The local x translation. */
//    public float getX () {
//        return x;
//    }
//
//    public void setX (float x) {
//        this.x = x;
//    }
//
//    /** The local y translation. */
//    public float getY () {
//        return y;
//    }
//
//    public void setY (float y) {
//        this.y = y;
//    }
//
//    /** The local scaleX. */
//    public float getScaleX () {
//        return scaleX;
//    }
//
//    public void setScaleX (float scaleX) {
//        this.scaleX = scaleX;
//    }
//
//    /** The local scaleY. */
//    public float getScaleY () {
//        return scaleY;
//    }
//
//    public void setScaleY (float scaleY) {
//        this.scaleY = scaleY;
//    }
//
//    /** The local rotation. */
//    public float getRotation () {
//        return rotation;
//    }
//
//    public void setRotation (float rotation) {
//        this.rotation = rotation;
//    }
//
//    /** The width of the region attachment in Spine. */
//    public float getWidth () {
//        return width;
//    }
//
//    public void setWidth (float width) {
//        this.width = width;
//    }
//
//    /** The height of the region attachment in Spine. */
//    public float getHeight () {
//        return height;
//    }
//
//    public void setHeight (float height) {
//        this.height = height;
//    }
//
//    /** The color to tint the region attachment. */
//    public Color getColor () {
//        return color;
//    }
//
//    /** The name of the texture region for this attachment. */
//    public String getPath () {
//        return path;
//    }
//
//    public void setPath (String path) {
//        this.path = path;
//    }
//}