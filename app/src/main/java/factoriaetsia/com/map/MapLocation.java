package factoriaetsia.com.map;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Point;
import android.graphics.RectF;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;

import factoriaetsia.com.openxmas.R;

/** Class to hold our location information */
public class MapLocation {

	public static final int TYPE_BUBBLE = 1;
	public static final int TYPE_BUBBLE_OFF = 2;
	public static final int TYPE_ANDROID = 3;
	public static final int TYPE_BACKGROUND= 4;

	public static final int TYPE_RENO = 10;
	public static final int TYPE_ARBOL = 11;
	public static final int TYPE_ELFO = 12;
	public static final int TYPE_NIEVE= 13;
	
	public static final int PADDING_X = 10;
	public static final int PADDING_Y = 8;
	public static final int RADIUS_BUBBLES = 5;
	public static final int DISTANCE_BUBBLE = 15;
	public static final int SIZE_SELECTOR_BUBBLE = 10;
	
	private GeoPoint point;
	

	private String name;
	private MapLocationViewer mapLocationView;
	private int type = -1;
	
	private Bitmap drawIcon, shadowIcon;

	public MapLocation(MapLocationViewer mapView, String name, double latitude, double longitude, int type) {
		this.name = name;
		mapLocationView = mapView;
		point = new GeoPoint((int)(latitude*1e6),(int)(longitude*1e6));
		setType(type);		
	}
	
	public MapLocation(MapLocationViewer mapView, String name, GeoPoint point, int type) {
		this.name = name;
		mapLocationView = mapView;
		this.point = point;
		setType(type);
	}

	private void setType(int type) {
		this.type = type;	
		switch (type) {
		case TYPE_BUBBLE:
			drawIcon = BitmapFactory.decodeResource(mapLocationView.getResources(), R.drawable.bubble);

			break;
			case TYPE_RENO:
				drawIcon = BitmapFactory.decodeResource(mapLocationView.getResources(), R.drawable.reno);

				break;
			case TYPE_NIEVE:
				drawIcon = BitmapFactory.decodeResource(mapLocationView.getResources(), R.drawable.nieveman);

				break;
			case TYPE_ARBOL:
				drawIcon = BitmapFactory.decodeResource(mapLocationView.getResources(), R.drawable.arbooool);

				break;
			case TYPE_ELFO:
				drawIcon = BitmapFactory.decodeResource(mapLocationView.getResources(), R.drawable.elf);

				break;
		case TYPE_ANDROID:
			drawIcon = BitmapFactory.decodeResource(mapLocationView.getResources(),R.drawable.android);

			break;
			case TYPE_BACKGROUND:
				drawIcon = BitmapFactory.decodeResource(mapLocationView.getResources(),R.drawable.mapafondo);

				break;
		default:
			drawIcon = BitmapFactory.decodeResource(mapLocationView.getResources(),R.drawable.bubble_off);

			break;
		}
	}
	
	public Bitmap getDrawIcon() {
		return drawIcon;
	}
	
	public Bitmap getShadowIcon() {
		return shadowIcon;
	}
	
	public int getType() {
		return type;
	}
	
	public GeoPoint getPoint() {
		return point;
	}
	public void setPoint(GeoPoint point) {
		this.point = point;
	}

	public String getName() {
		return name;
	}
	
	public int getWidthIcon() {
		return drawIcon.getWidth();
	}
	
	public int getHeightIcon() {
		return drawIcon.getHeight();
	}
	
	public int getWidthText() {
		return (int)MapLocationsManager.textPaint.measureText(this.getName());
	}
	
	public int getHeightText() {
		return (int)MapLocationsManager.textPaint.descent()-(int)MapLocationsManager.textPaint.ascent();
	}
	
	public RectF getHRectFIcon() {
		return getHRectFIcon(0, 0);
	}
	
	public RectF getHRectFIcon(int offsetx, int offsety) {
		RectF rectf = new RectF();
		rectf.set(-drawIcon.getWidth()/2,-drawIcon.getHeight(),drawIcon.getWidth()/2,0);
		rectf.offset(offsetx, offsety);
		return rectf;
	}
	
	public boolean getHit(int offsetx, int offsety, float event_x, float event_y) {
	    if ( getHRectFIcon(offsetx, offsety).contains(event_x,event_y) ) {
	        return true;
	    }
	    return false;
	}
	
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		Point p = new Point();
		mapView.getProjection().toPixels(this.getPoint(), p);
		
    	if (shadow) {
    		canvas.drawBitmap(this.getShadowIcon(), p.x, p.y - this.getShadowIcon().getHeight(),null);
    	} else {
			canvas.drawBitmap(this.getDrawIcon(), p.x -this.getDrawIcon().getWidth()/2, p.y -this.getDrawIcon().getHeight(),null);
    	}
	}
	
	public void drawBubble(Canvas canvas, MapView mapView, boolean shadow) {
	    Point p = new Point();
	    mapView.getProjection().toPixels(this.getPoint(), p);
	    
	    int wBox = getWidthText()  + (PADDING_X*2);
	    int hBox = getHeightText() + (PADDING_Y*2); 
	    
	    RectF boxRect = new RectF(0, 0, wBox, hBox);
	    int offsetX = p.x - wBox/2;
	    int offsetY = p.y - hBox - this.getHeightIcon() - DISTANCE_BUBBLE;
	    boxRect.offset(offsetX, offsetY);
	    
	    Path pathBubble = new Path();
	    pathBubble.addRoundRect(boxRect, RADIUS_BUBBLES, RADIUS_BUBBLES, Direction.CCW);
	    pathBubble.moveTo(offsetX+(wBox/2)-(SIZE_SELECTOR_BUBBLE/2), offsetY+hBox);
	    pathBubble.lineTo(offsetX+(wBox/2), offsetY+hBox+SIZE_SELECTOR_BUBBLE);
	    pathBubble.lineTo(offsetX+(wBox/2)+(SIZE_SELECTOR_BUBBLE/2), offsetY+hBox);
	    
	    canvas.drawPath(pathBubble, MapLocationsManager.borderPaint);
	    canvas.drawPath(pathBubble, MapLocationsManager.innerPaint);
	
	    canvas.drawText(this.getName(), p.x-(getWidthText()/2),
	    		p.y-MapLocationsManager.textPaint.ascent()-this.getHeightIcon()-hBox+PADDING_Y - DISTANCE_BUBBLE, MapLocationsManager.textPaint);
	}
	
}