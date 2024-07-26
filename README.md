# AnimationViewPlayground
자연스럽게 펼쳐지는 플러팅 버튼

![ExpandableFloatingButton_1](https://github.com/hydok/ExpandableFloatingButton/assets/26853549/be455f81-5795-440d-b89b-51f567404001)


## How to use
```code
        <com.hydok.expandablefloatingbutton.ExpandableFloatingButton
        android:id="@+id/floatingButton"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
```


```code
        //init
        floatingButton
            .setIcon(image = R.drawable.icon, widthDp = 40)
            .setTitle(title = "안녕하세요!!!", color = R.color.white, size = 18f)
            .setColor(R.color.blue)
            .setExpandDuration(300)

        //expand Event
        floatingButton.setExpanded() 

        //collapsed Event
        floatingButton.setCollapsed()
```


## Function
attribute programmatically
- `setIcon()`
- `setTitle()`
- `setColor()`
- `setExpandDuration()`
- `setExpanded()`
- `setCollapsed()`



# Overlap Card View
겹치고 펴지는 카드

![Screen_Recording_20240725_145518_ExpandableFloatingButton](https://github.com/user-attachments/assets/601c13a6-c275-4ba0-aa48-56a207b1b60d)



## How to use
```code
      <com.hydok.overlapcardview.OverlapCardView
        android:id="@+id/overlapCardView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"   />
```


```code
        //sample view
        val views =
            listOf(
                TextView(this).apply {
                    text = "1번"
                    setBackgroundColor(getColor(R.color.blue1))
                    setTextColor(getColor(R.color.white))
                    gravity = CENTER
                },
                TextView(this).apply {
                    text = "2번"
                    setBackgroundColor(getColor(R.color.blue2))
                    setTextColor(getColor(R.color.white))
                    gravity = CENTER
                })


        //init
        overlapView.setCard(views)
                   .setTopMargin(60,40)

        //change state
        btn.setOnClickListener {
            binding.overlapView.setStateChange()
        }
```


## Function
attribute programmatically
- `setCard()`
- `setDuration()`
- `setStateChange()`
- `setStateListener()`
- `getState()` 
