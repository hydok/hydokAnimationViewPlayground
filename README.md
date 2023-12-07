# ExpandableFloatingButton
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
       floatingButton
            .setIcon(R.drawable.icon)
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
- `clearView()`
- `setExpandDuration()`
- `setExpanded()`
- `setCollapsed()`

