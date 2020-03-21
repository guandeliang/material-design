/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-3-7 上午12:32
 *
 */

package com.jacob.material.base;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.jacob.material.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomImagePresenter {
    public enum ImageViewCategory{
        SQUARE, H_RECTANGLE, V_RECTANGLE;
    }
    private static List<Integer> squareImageList;
    private static List<Integer> hRectangleImageList;
    private static List<Integer> vRectangleImageList;

    static {
        initImageList();
    }
    private static void initImageList(){
        hRectangleImageList = new ArrayList<>();
        hRectangleImageList.add(R.drawable.grammy_singer_001);
        hRectangleImageList.add(R.drawable.grammy_singer_002);
        hRectangleImageList.add(R.drawable.grammy_singer_003);
        hRectangleImageList.add(R.drawable.grammy_singer_004);
        hRectangleImageList.add(R.drawable.grammy_singer_005);
        hRectangleImageList.add(R.drawable.grammy_singer_006);
        hRectangleImageList.add(R.drawable.grammy_singer_007);
        hRectangleImageList.add(R.drawable.grammy_singer_008);
        hRectangleImageList.add(R.drawable.grammy_singer_009);
        hRectangleImageList.add(R.drawable.grammy_singer_010);
        hRectangleImageList.add(R.drawable.img_480_270_01);
        hRectangleImageList.add(R.drawable.img_480_270_02);
        hRectangleImageList.add(R.drawable.img_480_270_03);
        hRectangleImageList.add(R.drawable.img_480_270_04);
        hRectangleImageList.add(R.drawable.img_480_270_05);
        hRectangleImageList.add(R.drawable.img_480_270_06);
        hRectangleImageList.add(R.drawable.img_480_270_07);
        hRectangleImageList.add(R.drawable.img_480_270_08);
        hRectangleImageList.add(R.drawable.img_480_270_09);
        hRectangleImageList.add(R.drawable.img_480_270_10);
        hRectangleImageList.add(R.drawable.img_480_270_11);
        hRectangleImageList.add(R.drawable.img_480_270_12);
        hRectangleImageList.add(R.drawable.img_720_01);
        hRectangleImageList.add(R.drawable.img_720_03);
        hRectangleImageList.add(R.drawable.img_720_04);
        hRectangleImageList.add(R.drawable.mv000);
        hRectangleImageList.add(R.drawable.mv001);
        hRectangleImageList.add(R.drawable.mv002);
        hRectangleImageList.add(R.drawable.mv003);
        hRectangleImageList.add(R.drawable.mv004);
        hRectangleImageList.add(R.drawable.mv005);
        hRectangleImageList.add(R.drawable.mv006);
        hRectangleImageList.add(R.drawable.mv009);
        hRectangleImageList.add(R.drawable.mv010);
        hRectangleImageList.add(R.drawable.mv011);
        hRectangleImageList.add(R.drawable.mv012);
        hRectangleImageList.add(R.drawable.mv013);
        hRectangleImageList.add(R.drawable.mv014);
        hRectangleImageList.add(R.drawable.mv015);
        hRectangleImageList.add(R.drawable.mv016);
        hRectangleImageList.add(R.drawable.mv017);
        hRectangleImageList.add(R.drawable.mv020);
        hRectangleImageList.add(R.drawable.mv021);
        hRectangleImageList.add(R.drawable.nba_003);
        hRectangleImageList.add(R.drawable.news_1_002);
        hRectangleImageList.add(R.drawable.news_1_003);
        hRectangleImageList.add(R.drawable.news_1_005);
        hRectangleImageList.add(R.drawable.news_1_006);
        hRectangleImageList.add(R.drawable.news_1_007);
        hRectangleImageList.add(R.drawable.news_1_008);
        hRectangleImageList.add(R.drawable.news_1_009);
        hRectangleImageList.add(R.drawable.news_2_005);
        hRectangleImageList.add(R.drawable.ring_002_001);
        hRectangleImageList.add(R.drawable.ring_002_002);
        hRectangleImageList.add(R.drawable.ring_003_001);
        hRectangleImageList.add(R.drawable.ring_003_002);
        hRectangleImageList.add(R.drawable.ring_003_003);
        hRectangleImageList.add(R.drawable.ring_003_004);
        hRectangleImageList.add(R.drawable.ring_004_001);
        hRectangleImageList.add(R.drawable.ring_004_002);
        hRectangleImageList.add(R.drawable.thrones_001_tyrion_lannister);
        hRectangleImageList.add(R.drawable.thrones_002_jaime_lannister);
        hRectangleImageList.add(R.drawable.thrones_003_cersei_lannister);
        hRectangleImageList.add(R.drawable.thrones_004_daenerys_targaryen);
        hRectangleImageList.add(R.drawable.thrones_005_jon_snow);
        hRectangleImageList.add(R.drawable.thrones_006_sansa_stark);
        hRectangleImageList.add(R.drawable.thrones_007_arya_stark);
        hRectangleImageList.add(R.drawable.thrones_008_bran_stark);
        hRectangleImageList.add(R.drawable.thrones_009_theon_greyjoy);
        hRectangleImageList.add(R.drawable.thrones_010_little_finger);
        hRectangleImageList.add(R.drawable.thrones_011_jorah_mormont);
        hRectangleImageList.add(R.drawable.thrones_012_hound);
        hRectangleImageList.add(R.drawable.thrones_013_bronn);
        hRectangleImageList.add(R.drawable.thrones_014_varys);
        hRectangleImageList.add(R.drawable.thrones_015_joffrey_baratheon);
        hRectangleImageList.add(R.drawable.thrones_016_ned_stark);
        hRectangleImageList.add(R.drawable.thrones_017_catelyn_stark);
        hRectangleImageList.add(R.drawable.thrones_018_robb_stark);
        hRectangleImageList.add(R.drawable.thrones_019_viserys_targaryen);
        hRectangleImageList.add(R.drawable.thrones_020_robert_baratheon);
        hRectangleImageList.add(R.drawable.thrones_021_samwell_tarly);
        hRectangleImageList.add(R.drawable.thrones_022_tywin_lannister);
        hRectangleImageList.add(R.drawable.thrones_023_shae);
        hRectangleImageList.add(R.drawable.thrones_024_jeor_mormont);
        hRectangleImageList.add(R.drawable.thrones_025_gendry);
        hRectangleImageList.add(R.drawable.thrones_026_tommen_baratheon);
        hRectangleImageList.add(R.drawable.thrones_027_jaqen_hghar);
        hRectangleImageList.add(R.drawable.thrones_028_khal_drogo);
        hRectangleImageList.add(R.drawable.thrones_029_margaery_tyrell);
        hRectangleImageList.add(R.drawable.thrones_030_daario_naharis);
        hRectangleImageList.add(R.drawable.thrones_030_stannis_baratheon);
        hRectangleImageList.add(R.drawable.thrones_031_melisandre);
        hRectangleImageList.add(R.drawable.thrones_032_davos_seaworth);
        hRectangleImageList.add(R.drawable.thrones_033_ygritte);
        hRectangleImageList.add(R.drawable.thrones_034_talisa_stark);
        hRectangleImageList.add(R.drawable.thrones_035_brienne_of_tarth);
        hRectangleImageList.add(R.drawable.thrones_036_gilly);
        hRectangleImageList.add(R.drawable.thrones_037_roose_bolton);
        hRectangleImageList.add(R.drawable.thrones_038_ramsay_bolton);
        hRectangleImageList.add(R.drawable.thrones_039_tormund);
        hRectangleImageList.add(R.drawable.thrones_041_missandei);
        hRectangleImageList.add(R.drawable.thrones_042_ellaria_sand);
        hRectangleImageList.add(R.drawable.thrones_043_high_sparrow);
        hRectangleImageList.add(R.drawable.thrones_044_grey_worm);

        squareImageList = new ArrayList<>();
        squareImageList.add(R.drawable.grammy_002_001);
        squareImageList.add(R.drawable.grammy_002_002);
        squareImageList.add(R.drawable.grammy_003_001);
        squareImageList.add(R.drawable.grammy_003_002);
        squareImageList.add(R.drawable.grammy_003_003);
        squareImageList.add(R.drawable.grammy_003_004);
        squareImageList.add(R.drawable.grammy_003_005);
        squareImageList.add(R.drawable.grammy_003_006);
        squareImageList.add(R.drawable.grammy_004_001);
        squareImageList.add(R.drawable.grammy_004_002);
        squareImageList.add(R.drawable.grammy_004_003);
        squareImageList.add(R.drawable.grammy_004_004);
        squareImageList.add(R.drawable.grammy_004_005);
        squareImageList.add(R.drawable.grammy_004_006);
        squareImageList.add(R.drawable.grammy_006_001);
        squareImageList.add(R.drawable.grammy_006_002);
        squareImageList.add(R.drawable.grammy_006_003);
        squareImageList.add(R.drawable.grammy_006_004);
        squareImageList.add(R.drawable.grammy_006_005);
        squareImageList.add(R.drawable.grammy_006_006);
        squareImageList.add(R.drawable.grammy_008_001);
        squareImageList.add(R.drawable.grammy_008_002);
        squareImageList.add(R.drawable.grammy_008_003);
        squareImageList.add(R.drawable.grammy_008_004);
        squareImageList.add(R.drawable.grammy_008_005);
        squareImageList.add(R.drawable.grammy_008_006);
        squareImageList.add(R.drawable.grammy_008_007);
        squareImageList.add(R.drawable.grammy_008_008);
        squareImageList.add(R.drawable.grammy_008_009);
        squareImageList.add(R.drawable.header_001);
        squareImageList.add(R.drawable.header_002);
        squareImageList.add(R.drawable.header_003);
        squareImageList.add(R.drawable.header_004);
        squareImageList.add(R.drawable.header_005);
        squareImageList.add(R.drawable.header_006);
        squareImageList.add(R.drawable.header_007);
        squareImageList.add(R.drawable.header_008);
        squareImageList.add(R.drawable.header_009);
        squareImageList.add(R.drawable.img_480_480_01);
        squareImageList.add(R.drawable.weipinhui_light_501);
        squareImageList.add(R.drawable.weipinhui_light_502);
        squareImageList.add(R.drawable.weipinhui_light_503);
        squareImageList.add(R.drawable.weipinhui_light_504);

        vRectangleImageList = new ArrayList<>();
        vRectangleImageList.add(R.drawable.book_002_001);
        vRectangleImageList.add(R.drawable.book_002_002);
        vRectangleImageList.add(R.drawable.book_002_003);
        vRectangleImageList.add(R.drawable.book_002_004);
        vRectangleImageList.add(R.drawable.book_002_005);
        vRectangleImageList.add(R.drawable.book_002_006);
        vRectangleImageList.add(R.drawable.book_002_007);
        vRectangleImageList.add(R.drawable.book_002_008);
        vRectangleImageList.add(R.drawable.cd_007_001);
        vRectangleImageList.add(R.drawable.cd_007_002);
        vRectangleImageList.add(R.drawable.cd_007_003);
        vRectangleImageList.add(R.drawable.grammy_007_001);
        vRectangleImageList.add(R.drawable.grammy_007_002);
        vRectangleImageList.add(R.drawable.grammy_007_003);
        vRectangleImageList.add(R.drawable.grammy_007_004);
        vRectangleImageList.add(R.drawable.grammy_007_005);
        vRectangleImageList.add(R.drawable.grammy_007_006);
        vRectangleImageList.add(R.drawable.grammy_007_007);
        vRectangleImageList.add(R.drawable.grammy_007_008);
        vRectangleImageList.add(R.drawable.grammy_007_009);
        vRectangleImageList.add(R.drawable.img_480_720_01);
        vRectangleImageList.add(R.drawable.img_480_720_02);
        vRectangleImageList.add(R.drawable.img_480_720_03);
        vRectangleImageList.add(R.drawable.img_480_720_04);
        vRectangleImageList.add(R.drawable.news_2_001);
        vRectangleImageList.add(R.drawable.news_2_008);
        vRectangleImageList.add(R.drawable.weipinhui_light_001);
        vRectangleImageList.add(R.drawable.weipinhui_light_002);
        vRectangleImageList.add(R.drawable.weipinhui_light_003);
        vRectangleImageList.add(R.drawable.weipinhui_light_101);
        vRectangleImageList.add(R.drawable.weipinhui_light_102);
        vRectangleImageList.add(R.drawable.weipinhui_light_103);
        vRectangleImageList.add(R.drawable.weipinhui_light_201);
        vRectangleImageList.add(R.drawable.weipinhui_light_202);
        vRectangleImageList.add(R.drawable.weipinhui_light_203);
        vRectangleImageList.add(R.drawable.weipinhui_light_301);
        vRectangleImageList.add(R.drawable.weipinhui_light_302);
        vRectangleImageList.add(R.drawable.weipinhui_light_303);
        vRectangleImageList.add(R.drawable.weipinhui_light_401);
        vRectangleImageList.add(R.drawable.weipinhui_light_402);
        vRectangleImageList.add(R.drawable.weipinhui_light_403);

    }
    @BindingAdapter("imageViewCategory")
    public static void setImageViewSrc(ImageView imageView, ImageViewCategory category) {
        Random random = new Random();
        if(category == ImageViewCategory.SQUARE){
            imageView.setImageResource(squareImageList.get(random.nextInt(squareImageList.size())));
        }else if(category == ImageViewCategory.H_RECTANGLE){
            imageView.setImageResource(hRectangleImageList.get(random.nextInt(hRectangleImageList.size())));
        }else if(category == ImageViewCategory.V_RECTANGLE){
            imageView.setImageResource(vRectangleImageList.get(random.nextInt(vRectangleImageList.size())));
        }
    }
}
