package com.example.test

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_createbouquet.*


class BouquetActivity:AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.Q)

    override fun onCreate(savedInstanceState: Bundle?) {
        var nrRoses = 0
        var nrGermini = 0
        var nrPeonies = 0
        var nrLilies = 0
        var nrFreesia = 0
        var nrBouvardia = 0
        var nrCarnations = 0
        var nrHyancnths = 0
        var nrAnthurium = 0
        var nrAgapanthus = 0
        var nrTullips = 0
        var nrLiliac = 0
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createbouquet)

        val btnProfile: ImageButton = findViewById(R.id.imageButton3)
        btnProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        val btnHome: ImageButton = findViewById(R.id.ButtonHome)
        btnHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        fun appear(b: ImageButton, f: Array<ImageButton>): Boolean {
            for(i in f.indices) {
                if(b.id == f[i].id){
                    return true
                }
            }
            return false
        }
        fun searchFlowers(f: Array<ImageButton>): Boolean {
            val flowers = arrayOf(buttonRoses, buttonGermini, buttonPeonies, buttonLilies, buttonFreesia, buttonBouvardia,
                buttonCarnations, buttonHyancnths, buttonAnthurium, buttonAgapanthus, buttonTullips, buttonLiliac)

            for(i in flowers.indices) {
                if(appear(flowers[i], f) and !flowers[i].isSelected){
                    return false
                }
                if(!appear(flowers[i], f) and flowers[i].isSelected){
                    return false
                }

            }
            return true
        }
        val buttonSubmit : Button = findViewById(R.id.buttonSubmit)
        buttonSubmit.setOnClickListener {
            buttonSubmit.visibility = View.INVISIBLE
            buttonSubmit.isSelected = true

            val buttonReconfigure : Button = findViewById(R.id.buttonConfigureAgain)
            buttonReconfigure.text = "Configure again"

            val textBouquet : TextView = findViewById(R.id.textBouquet)
            var text = "Your bouquet consists of: \n"
            val flowers = arrayOf(buttonRoses, buttonGermini, buttonPeonies, buttonLilies, buttonFreesia, buttonBouvardia,
                                  buttonCarnations, buttonHyancnths, buttonAnthurium, buttonAgapanthus, buttonTullips, buttonLiliac)
            val nrFlowers = arrayOf(nrRoses, nrGermini, nrPeonies, nrLilies, nrFreesia, nrBouvardia, nrCarnations, nrHyancnths,
                                    nrAnthurium, nrAgapanthus, nrTullips, nrLiliac)
            var totalCost = 0
            for(i in flowers.indices) {
                if(flowers[i].isSelected) {
                    text = text.plus(flowers[i].contentDescription.split(' ', '(')[0]).plus(": ").plus(nrFlowers[i]).plus('\n')
                    totalCost += (nrFlowers[i] * flowers[i].contentDescription.split(' ', '(')[2].toInt())
                }
            }
            val ornaments = arrayOf(buttonOrnament1, buttonOrnament2, buttonOrnament3, buttonOrnament4)
            text = text.plus("Ornaments: ")
            for(i in ornaments.indices){
                if(ornaments[i].isSelected){
                    text = text.plus(ornaments[i].contentDescription).plus(", ")
                }
            }
            text += ("\n")
            val wraps = arrayOf(buttonWrap1, buttonWrap2, buttonWrap3, buttonWrap4, buttonWrap5, buttonWrap6)
            text += ("The wrap of the bouquet: ")
            for(i in wraps.indices){
                if(wraps[i].isSelected)
                    text = text.plus(wraps[i].contentDescription).plus('\n')
            }
            text = text.plus("\n").plus("Total cost: ").plus(totalCost).plus(" de lei\n")
            textBouquet.text = text

            val bouquetImage : ImageView = findViewById(R.id.bouquetImage)
            bouquetImage.visibility = View.VISIBLE

            when {
                searchFlowers(arrayOf(buttonRoses, buttonTullips)) -> {
                    bouquetImage.setImageResource(R.drawable.roses_and_tullips)
                }
                searchFlowers(arrayOf(buttonRoses, buttonTullips, buttonPeonies)) -> {
                    bouquetImage.setImageResource(R.drawable.roses_tullips_peonies)
                }
                searchFlowers(arrayOf(buttonRoses, buttonAgapanthus)) -> {
                    bouquetImage.setImageResource(R.drawable.roses_agapanthus)
                }
                searchFlowers(arrayOf(buttonTullips, buttonHyancnths)) -> {
                    bouquetImage.setImageResource(R.drawable.tullips_hyancinths)
                }
                searchFlowers(arrayOf(buttonRoses, buttonLilies)) -> {
                    bouquetImage.setImageResource(R.drawable.roses_lilies)
                }
                searchFlowers(arrayOf(buttonRoses, buttonLilies, buttonFreesia)) -> {
                    bouquetImage.setImageResource(R.drawable.roses_lilies_freesia)
                }
                searchFlowers(arrayOf(buttonLilies, buttonFreesia)) -> {
                    bouquetImage.setImageResource(R.drawable.lilies_freesia)
                }
                searchFlowers(arrayOf(buttonCarnations)) -> {
                    bouquetImage.setImageResource(R.drawable.carnations_)
                }
                searchFlowers(arrayOf(buttonLiliac, buttonHyancnths)) -> {
                    bouquetImage.setImageResource(R.drawable.liliac_hyancinths)
                }
                searchFlowers(arrayOf(buttonPeonies, buttonBouvardia)) -> {
                    bouquetImage.setImageResource(R.drawable.peonies_bouvardia)
                }
                searchFlowers(arrayOf(buttonRoses, buttonAgapanthus)) -> {
                    bouquetImage.setImageResource(R.drawable.roses_agapanthus)
                }
                searchFlowers(arrayOf(buttonRoses, buttonLilies, buttonGermini)) -> {
                    bouquetImage.setImageResource(R.drawable.roses_lilies_germini)
                }
                searchFlowers(arrayOf(buttonPeonies)) -> {
                    bouquetImage.setImageResource(R.drawable.peonies_)
                }
                searchFlowers(arrayOf(buttonLilies)) -> {
                    bouquetImage.setImageResource(R.drawable.lilies_)
                }
                searchFlowers(arrayOf(buttonRoses)) -> {
                    bouquetImage.setImageResource(R.drawable.roses_)
                }
                else -> {
                    bouquetImage.setImageResource(R.drawable.inexistent_bouquet)
                }
            }

        }
            val buttonRoses: ImageButton = findViewById(R.id.buttonRoses)
            val buttonCancelRoses : Button = findViewById(R.id.cancelRoses)
            buttonRoses.setOnClickListener{
                val textRoses : TextView = findViewById(R.id.numberRoses)
                if(!buttonSubmit.isSelected){
                    if(!buttonRoses.isSelected) {
                        nrRoses = 1
                        buttonRoses.isSelected = true
                        buttonRoses.imageAlpha = 100
                        buttonCancelRoses.visibility = View.VISIBLE
                    }
                    else
                        nrRoses++

                    textRoses.text = nrRoses.toString()
                }


            }
            buttonCancelRoses.setOnClickListener {
                if(!buttonSubmit.isSelected){
                    val textRoses : TextView = findViewById(R.id.numberRoses)
                    nrRoses = 0
                    buttonRoses.imageAlpha = 255
                    buttonRoses.isSelected = false
                    textRoses.text = null
                    buttonCancelRoses.visibility = View.INVISIBLE
                }

            }

            val buttonGermini : ImageButton = findViewById(R.id.buttonGermini)
            val buttonCancelGermini : Button = findViewById(R.id.cancelGermini)
            buttonGermini.setOnClickListener{
                val textGermini : TextView = findViewById(R.id.numberGermini)
                if(!buttonSubmit.isSelected){
                    if (!buttonGermini.isSelected){
                        buttonGermini.isSelected = true
                        buttonGermini.imageAlpha = 100
                        nrGermini = 1
                        buttonCancelGermini.visibility = View.VISIBLE

                    }else
                        nrGermini++

                    textGermini.text = nrGermini.toString()
                }

            }
            buttonCancelGermini.setOnClickListener {
                if(!buttonSubmit.isSelected){
                    val textGermini : TextView = findViewById(R.id.numberGermini)
                    textGermini.text = null
                    nrGermini = 0
                    buttonGermini.imageAlpha = 255
                    buttonGermini.isSelected = false
                    buttonCancelGermini.visibility = View.INVISIBLE
                }
            }
            val buttonPeonies : ImageButton = findViewById(R.id.buttonPeonies)
            val buttonCancelPeonies : Button = findViewById(R.id.cancelPeonies)
            buttonPeonies.setOnClickListener{
                if(!buttonSubmit.isSelected){
                    val textPeonies : TextView  = findViewById(R.id.numberPeonies)
                    if (!buttonPeonies.isSelected){
                        buttonPeonies.isSelected = true
                        buttonPeonies.imageAlpha = 100
                        nrPeonies = 1
                        buttonCancelPeonies.visibility = View.VISIBLE
                    }else
                        nrPeonies++

                    textPeonies.text = nrPeonies.toString()
                }
            }
            buttonCancelPeonies.setOnClickListener {
                if(!buttonSubmit.isSelected){
                    val textPeonies : TextView  = findViewById(R.id.numberPeonies)
                    textPeonies.text = null
                    nrPeonies = 0
                    buttonCancelPeonies.visibility = View.INVISIBLE
                    buttonPeonies.imageAlpha = 255
                    buttonPeonies.isSelected = false
                }
            }
            val buttonLilies : ImageButton = findViewById(R.id.buttonLilies)
            val buttonCancelLilies : Button = findViewById(R.id.cancelLilies)
            buttonLilies.setOnClickListener {
                if(!buttonSubmit.isSelected){
                    val textLilies : TextView = findViewById(R.id.numberLilies)
                    if (!buttonLilies.isSelected){
                        buttonLilies.isSelected = true
                        buttonLilies.imageAlpha = 100
                        nrLilies = 1
                        buttonCancelLilies.visibility = View.VISIBLE

                    }else
                        nrLilies++

                    textLilies.text = nrLilies.toString()
                }
            }
            buttonCancelLilies.setOnClickListener {
                if(!buttonSubmit.isSelected){
                    val textLilies : TextView = findViewById(R.id.numberLilies)
                    textLilies.text = null
                    nrLilies = 0
                    buttonLilies.imageAlpha = 255
                    buttonLilies.isSelected = false
                    buttonCancelLilies.visibility = View.INVISIBLE
                }
            }
            val buttonFreesia : ImageButton = findViewById(R.id.buttonFreesia)
            val buttonCancelFreesia : Button = findViewById(R.id.cancelFreesia)
            buttonFreesia.setOnClickListener {
                if(!buttonSubmit.isSelected){
                    val textFreesia : TextView = findViewById(R.id.numberFreesia)
                    if (!buttonFreesia.isSelected){
                        buttonFreesia.isSelected = true
                        buttonFreesia.imageAlpha = 100
                        nrFreesia = 1
                        buttonCancelFreesia.visibility = View.VISIBLE
                    }else
                        nrFreesia++

                    textFreesia.text = nrFreesia.toString()
                }
            }
            buttonCancelFreesia.setOnClickListener {
                if(!buttonSubmit.isSelected){
                    val textFreesia : TextView = findViewById(R.id.numberFreesia)
                    textFreesia.text = null
                    nrFreesia = 0
                    buttonFreesia.imageAlpha = 255
                    buttonFreesia.isSelected = false
                    buttonCancelFreesia.visibility = View.INVISIBLE
                }
            }
            val buttonBouvardia : ImageButton = findViewById(R.id.buttonBouvardia)
            val buttonCancelBouvardia : Button = findViewById(R.id.cancelBouvardia)
            buttonBouvardia.setOnClickListener {
                if(!buttonSubmit.isSelected){
                    val textBouvardia : TextView = findViewById(R.id.numberBouvardia)
                    if (!buttonBouvardia.isSelected){
                        buttonBouvardia.isSelected = true
                        buttonBouvardia.imageAlpha = 100
                        nrBouvardia = 1
                        buttonCancelBouvardia.visibility = View.VISIBLE

                    }else
                        nrBouvardia++

                    textBouvardia.text = nrBouvardia.toString()
                }
            }
            buttonCancelBouvardia.setOnClickListener {
                if(!buttonSubmit.isSelected){
                    val textBouvardia : TextView = findViewById(R.id.numberBouvardia)
                    textBouvardia.text = null
                    nrBouvardia = 0
                    buttonBouvardia.imageAlpha = 255
                    buttonBouvardia.isSelected = false
                    buttonCancelBouvardia.visibility = View.INVISIBLE
                }
            }
            val buttonCarnations : ImageButton = findViewById(R.id.buttonCarnations)
            val buttonCancelCarnations : Button = findViewById(R.id.cancelCarnations)
            buttonCarnations.setOnClickListener {
                if(!buttonSubmit.isSelected){
                    val textCarnations : TextView = findViewById(R.id.numberCarnations)
                    if (!buttonCarnations.isSelected){
                        buttonCarnations.isSelected = true
                        buttonCarnations.imageAlpha = 100
                        nrCarnations = 1
                        buttonCancelCarnations.visibility = View.VISIBLE


                    }else
                        nrCarnations++

                    textCarnations.text = nrCarnations.toString()
                }

            }
            buttonCancelCarnations.setOnClickListener {
                if(!buttonSubmit.isSelected){
                    val textCarnations : TextView = findViewById(R.id.numberCarnations)
                    textCarnations.text = null
                    nrCarnations = 0
                    buttonCarnations.imageAlpha = 255
                    buttonCarnations.isSelected = false
                    buttonCancelCarnations.visibility = View.INVISIBLE
                }
            }
            val buttonHyancnths : ImageButton = findViewById(R.id.buttonHyancnths)
            val buttonCancelHyancnths : Button = findViewById(R.id.cancelHyancnths)
            buttonHyancnths.setOnClickListener {
                if(!buttonSubmit.isSelected){
                    val textHyancnths : TextView = findViewById(R.id.numberHyancnths)
                    if (!buttonHyancnths.isSelected){
                        buttonHyancnths.isSelected = true
                        buttonHyancnths.imageAlpha = 100
                        nrHyancnths = 1
                        buttonCancelHyancnths.visibility = View.VISIBLE
                    }else
                        nrHyancnths++

                    textHyancnths.text = nrHyancnths.toString()
                }

            }
            buttonCancelHyancnths.setOnClickListener {
                if(!buttonSubmit.isSelected){
                    val textHyancnths : TextView = findViewById(R.id.numberHyancnths)
                    textHyancnths.text = null
                    nrHyancnths = 0
                    buttonHyancnths.imageAlpha = 255
                    buttonHyancnths.isSelected = false
                    buttonCancelHyancnths.visibility = View.INVISIBLE
                }

            }
            val buttonAnthurium : ImageButton = findViewById(R.id.buttonAnthurium)
            val buttonCancelAnthurium : Button = findViewById(R.id.cancelAnthurium)
            buttonAnthurium.setOnClickListener {
                if(!buttonSubmit.isSelected){
                    val textAnthurium : TextView = findViewById(R.id.numberAnthurium)
                    if (!buttonAnthurium.isSelected){
                        buttonAnthurium.isSelected = true
                        buttonAnthurium.imageAlpha = 100
                        nrAnthurium = 1
                        buttonCancelAnthurium.visibility = View.VISIBLE
                    }else
                        nrAnthurium++

                    textAnthurium.text = nrAnthurium.toString()
                }
            }
            buttonCancelAnthurium.setOnClickListener {
                if(!buttonSubmit.isSelected){
                    val textAnthurium : TextView = findViewById(R.id.numberAnthurium)
                    textAnthurium.text = null
                    nrAnthurium = 0
                    buttonAnthurium.imageAlpha = 255
                    buttonAnthurium.isSelected = false
                    buttonCancelAnthurium.visibility = View.INVISIBLE
                }

            }
            val buttonAgapanthus : ImageButton = findViewById(R.id.buttonAgapanthus)
            val buttonCancelAgapanthus : Button = findViewById(R.id.cancelAgapanthus)
            buttonAgapanthus.setOnClickListener {
                if(!buttonSubmit.isSelected){
                    val textAgapanthus : TextView = findViewById(R.id.numberAgapanthus)
                    if (!buttonAgapanthus.isSelected){
                        buttonAgapanthus.isSelected = true
                        buttonAgapanthus.imageAlpha = 100
                        nrAgapanthus = 1
                        buttonCancelAgapanthus.visibility = View.VISIBLE
                    }else
                        nrAgapanthus++

                    textAgapanthus.text = nrAgapanthus.toString()
                }
            }
            buttonCancelAgapanthus.setOnClickListener {
                if(!buttonSubmit.isSelected){
                    val textAgapanthus : TextView = findViewById(R.id.numberAgapanthus)
                    textAgapanthus.text = null
                    nrAgapanthus = 0
                    buttonAgapanthus.imageAlpha = 255
                    buttonAgapanthus.isSelected = false
                    buttonCancelAgapanthus.visibility = View.INVISIBLE
                }
            }
            val buttonTullips : ImageButton = findViewById(R.id.buttonTullips)
            val buttonCancelTullips : Button = findViewById(R.id.cancelTullips)
            buttonTullips.setOnClickListener {
                if(!buttonSubmit.isSelected){
                    val textTullips : TextView = findViewById(R.id.numberTullips)
                    if (!buttonTullips.isSelected){
                        buttonTullips.isSelected = true
                        buttonTullips.imageAlpha = 100
                        nrTullips = 1
                        buttonCancelTullips.visibility = View.VISIBLE
                    }else
                        nrTullips++

                    textTullips.text = nrTullips.toString()
                }
            }
            buttonCancelTullips.setOnClickListener {
                if(!buttonSubmit.isSelected){
                    val textTullips : TextView = findViewById(R.id.numberTullips)
                    textTullips.text = null
                    nrTullips = 0
                    buttonTullips.imageAlpha = 255
                    buttonTullips.isSelected = false
                    buttonCancelTullips.visibility = View.INVISIBLE
                }
            }
            val buttonLiliac : ImageButton = findViewById(R.id.buttonLiliac)
            val buttonCancelLiliac : Button = findViewById(R.id.cancelLiliac)
            buttonLiliac.setOnClickListener {
                if(!buttonSubmit.isSelected){
                    val textLiliac : TextView = findViewById(R.id.numberLiliac)
                    if (!buttonLiliac.isSelected){
                        buttonLiliac.isSelected = true
                        buttonLiliac.imageAlpha = 100
                        nrLiliac = 1
                        buttonCancelLiliac.visibility = View.VISIBLE
                    }else
                        nrLiliac++

                    textLiliac.text = nrLiliac.toString()
                }
            }
            buttonCancelLiliac.setOnClickListener {
                if(!buttonSubmit.isSelected){
                    val textLiliac : TextView = findViewById(R.id.numberLiliac)
                    textLiliac.text = null
                    nrLiliac = 0
                    buttonLiliac.imageAlpha = 255
                    buttonLiliac.isSelected = false
                    buttonCancelLiliac.visibility = View.INVISIBLE
                }
            }
            val buttonOrnament1 : ImageButton = findViewById(R.id.buttonOrnament1)
            buttonOrnament1.setOnClickListener {
                if(!buttonSubmit.isSelected){
                    if (buttonOrnament1.isSelected){
                        buttonOrnament1.isSelected = false
                        buttonOrnament1.imageAlpha = 255


                    }else {
                        buttonOrnament1.isSelected = true
                        buttonOrnament1.imageAlpha = 100
                    }
                }
            }

            val buttonOrnament2 : ImageButton = findViewById(R.id.buttonOrnament2)
            buttonOrnament2.setOnClickListener {
                if(!buttonSubmit.isSelected){
                    if (buttonOrnament2.isSelected){
                        buttonOrnament2.isSelected = false
                        buttonOrnament2.imageAlpha = 255


                    }else {
                        buttonOrnament2.isSelected = true
                        buttonOrnament2.imageAlpha = 100
                    }
                }
            }

            val buttonOrnament3 : ImageButton = findViewById(R.id.buttonOrnament3)
            buttonOrnament3.setOnClickListener {
                if(!buttonSubmit.isSelected){
                    if (buttonOrnament3.isSelected){
                        buttonOrnament3.isSelected = false
                        buttonOrnament3.imageAlpha = 255


                    }else {
                        buttonOrnament3.isSelected = true
                        buttonOrnament3.imageAlpha = 100
                    }
                }
            }

            val buttonOrnament4 : ImageButton = findViewById(R.id.buttonOrnament4)
            buttonOrnament4.setOnClickListener {
                if(!buttonSubmit.isSelected){
                    if (buttonOrnament4.isSelected){
                        buttonOrnament4.isSelected = false
                        buttonOrnament4.imageAlpha = 255


                    }else {
                        buttonOrnament4.isSelected = true
                        buttonOrnament4.imageAlpha = 100
                    }
                }
            }

            val buttonWrap1  :ImageButton = findViewById(R.id.buttonWrap1)
            val buttonWrap2  :ImageButton = findViewById(R.id.buttonWrap2)
            val buttonWrap3  :ImageButton = findViewById(R.id.buttonWrap3)
            val buttonWrap4  :ImageButton = findViewById(R.id.buttonWrap4)
            val buttonWrap5  :ImageButton = findViewById(R.id.buttonWrap5)
            val buttonWrap6  :ImageButton = findViewById(R.id.buttonWrap6)
            val wraps = arrayOf(buttonWrap1, buttonWrap2, buttonWrap3, buttonWrap4, buttonWrap5, buttonWrap6)

                for(i in wraps.indices){
                    wraps[i].setOnClickListener {
                        if(!buttonSubmit.isSelected){
                            if(wraps[i].isSelected) {
                                wraps[i].isSelected = false
                                wraps[i].imageAlpha = 255
                            }
                            else {
                                for(j in wraps.indices) {
                                    if(i != j) {
                                        wraps[j].isSelected = false
                                        wraps[j].imageAlpha = 255
                                    }
                                }
                                wraps[i].isSelected = true
                                wraps[i].imageAlpha = 100
                            }
                        }
                    }
                }


        val buttonReconfigure : Button = findViewById(R.id.buttonConfigureAgain)
        buttonReconfigure.setOnClickListener {

            buttonSubmit.visibility = View.VISIBLE
            buttonSubmit.isSelected = false

            val textBouquet: TextView = findViewById(R.id.textBouquet)
            textBouquet.text = null

            val bouquetCreated: ImageView = findViewById(R.id.bouquetImage)
            bouquetCreated.visibility = View.INVISIBLE
            bouquetCreated.setImageResource(R.drawable.none_image)

            buttonReconfigure.text = "Press to go up"
            scrollView2.fullScroll(ScrollView.FOCUS_UP)
        }
    }
}