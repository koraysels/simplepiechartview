simplepiechartview
==================

A simple PieChart library for Android

Instructions
---------------------------------------

Import the jar to your project and create a ist will contain all seperate Pies

`List<PieItem> pieData = new ArrayList<PieItem>(0);` 

Create your Pie pieces like this:	

`new PieItem(index, labelString,(float) percentage, color);`

And add them all to the PieData List,then you generate a Bitmap to draw the pie to like so 
 `Bitmap mBackgroundImage = Bitmap.createBitmap(Size, Size,
                Bitmap.Config.ARGB_4444);`

          PieChartView pieChartView = new PieChartView(getBaseContext());

          pieChartView.setLabelFont(yourTypeface, textSize, textColor );
          pieChartView.setGeometry(size, size, 0, 0, 0, 0);
          pieChartView.setData(pieData);
          pieChartView.invalidate();
          
          pieChartView.draw(new Canvas(mBackgroundImage));
          
          ImageView mPieImageView = new ImageView(context);
    
          mPieImageView.setBackgroundColor(Color.TRANSPARENT);
          mPieImageView.setImageBitmap(mBackgroundImage);
          
Now you are ready to add the image view to any view you want. 

####example xml layout file:

        <LinearLayout
          android:id="@+id/pie_container"
          android:layout_width="fill_parent"
          android:layout_height="fill_parent"
          android:orientation="vertical" 
          android:layout_gravity="center"
          android:gravity="center"
          android:layout_marginTop="20dp"
          android:layout_marginBottom="20dp"
          android:background="@android:color/transparent">
        </LinearLayout>
      
        ((LinearLayout) findViewById(R.id.pie_container)).addView(mPieImageView);

###### Credits:
This library is based on the classes of Laurent Moulinier from Gafmedia Studio, published in april 2010. 
