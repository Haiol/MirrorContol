package com.lovehp30.mirrorcontrol;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextPaint;
import android.widget.ListView;
import android.widget.Toast;

import com.magicgoop.tagsphere.OnTagTapListener;
import com.magicgoop.tagsphere.TagSphereView;
import com.magicgoop.tagsphere.item.TagItem;
import com.magicgoop.tagsphere.item.TextTagItem;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataActivity extends AppCompatActivity {
    TagSphereView tagSphereView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        String[] list =
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean rutrum mollis interdum. Donec imperdiet condimentum faucibus. Aliquam vel ex pulvinar, consectetur mi ac, scelerisque lorem. Suspendisse sit amet bibendum orci. In quis nisl dapibus, faucibus tellus sit amet, venenatis lorem. Donec luctus luctus ultrices. In tellus diam, gravida vitae pellentesque et, tristique eu nisl. Donec pretium erat sed augue lobortis consectetur. Quisque et eleifend tortor.Ut blandit fermentum cursus. Aliquam at rhoncus nisi, et consectetur est. Quisque malesuada est leo, in cursus magna consectetur at. Cras et volutpat justo. Vestibulum condimentum dictum molestie. Phasellus aliquam, diam sed interdum commodo, diam purus egestas massa, in dictum tortor erat quis dolor. Donec dapibus dolor quis mi commodo finibus. Vivamus fermentum tellus nulla, iaculis pharetra urna elementum in."
                        .replace("[^a-zA-Z\\s]", "")
                        .replace("\\s+", " ")
                        .split(" ");

        tagSphereView = findViewById(R.id.tagView);
        TextPaint paint = new TextPaint();
        paint.setAntiAlias(true);
        paint.setTextSize(40f);
        paint.setColor(Color.BLACK);
        List<TextTagItem> item = new ArrayList<>();
        for(String s: list)
            item.add(new TextTagItem(s));
        tagSphereView.setTextPaint(paint);
        tagSphereView.addTagList(item);
        tagSphereView.setRadius(2.5f);
        tagSphereView.setOnTagTapListener(new OnTagTapListener() {
            @Override
            public void onTap(@NotNull TagItem tagItem) {
            }
        });
    }
}