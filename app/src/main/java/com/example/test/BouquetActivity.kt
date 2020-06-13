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
    var itemList: ArrayList<Item> = ArrayList()
    var totalCost = 0

    override fun onCreate(savedInstanceState: Bundle?) {


        // urmatoarele 12 variabile vor contoriza cate flori de tipul respectiv au fost selectate(initial toate 0)
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
        setContentView(R.layout.activity_createbouquet) //setez continutul paginii

        val btnProfile: ImageButton = findViewById(R.id.imageButton3) //buton profil care redirectioneaza catrea pagina "Profile"
        btnProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        val btnHome: ImageButton = findViewById(R.id.ButtonHome) // redirectionare catre pagina principala
        btnHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        val btnPlaceOrder : Button = findViewById(R.id.buttonPlaceOrder)
        btnPlaceOrder.setOnClickListener {
            val intent  = Intent(this@BouquetActivity, PlaceOrderActivity::class.java)
            val flowers = arrayOf(buttonRoses, buttonGermini, buttonPeonies, buttonLilies, buttonFreesia, buttonBouvardia,
                buttonCarnations, buttonHyancnths, buttonAnthurium, buttonAgapanthus, buttonTullips, buttonLiliac)
            val nrFlowers = arrayOf(nrRoses, nrGermini, nrPeonies, nrLilies, nrFreesia, nrBouvardia, nrCarnations, nrHyancnths,
                nrAnthurium, nrAgapanthus, nrTullips, nrLiliac)
            for (i in flowers.indices){
                if (flowers[i].isSelected){
                    val i: Item = Item(flowers[i].contentDescription.split(' ', '(')[0], flowers[i].contentDescription.split(' ', '(')[2],null, nrFlowers[i], nrFlowers[i] * flowers[i].contentDescription.split(' ', '(')[2].toInt())
                    itemList.add(i)
                }
            }

            intent.putExtra("itemList", itemList)
            startActivity(intent)

        }
        fun appear(b: ImageButton, f: Array<ImageButton>): Boolean {
            // functia verifica daca un anume buton apare intr-o lista de butoane, returneaza true in caz afirmativ, false in caz contrar
            for(i in f.indices) {
                if(b.id == f[i].id){
                    return true
                }
            }
            return false
        }
        fun searchFlowers(f: Array<ImageButton>): Boolean {
            //verifica daca butoanele din lista 'f' de butoane, care sunt butoane din cele 12 butoane cu flori, sunt toate selectate
            val flowers = arrayOf(buttonRoses, buttonGermini, buttonPeonies, buttonLilies, buttonFreesia, buttonBouvardia,
                buttonCarnations, buttonHyancnths, buttonAnthurium, buttonAgapanthus, buttonTullips, buttonLiliac)
            for(i in flowers.indices) { //pentru fiecare buton din 'flowers', verific daca acesta apare in 'f'
                if(appear(flowers[i], f) and !flowers[i].isSelected){ /// daca apare si nu este selectat, return false
                    return false
                }
                if(!appear(flowers[i], f) and flowers[i].isSelected){ // daca nu apare, dar el este selectat, return false
                    return false
                }

            }
            return true // in final, inseamna ca toate butoanele din 'flowers' care apar in 'f' sunt selectate
        }
        val buttonSubmit : Button = findViewById(R.id.buttonSubmit) // buton de finalizare a configurarii butonului
        buttonSubmit.setOnClickListener {

            buttonSubmit.visibility = View.INVISIBLE
            buttonSubmit.isSelected = true

            val buttonReconfigure : Button = findViewById(R.id.buttonConfigureAgain) // permite reconfigurarea buchetului
            buttonReconfigure.text = "Configure again"

            val textBouquet : TextView = findViewById(R.id.textBouquet) // se afiseaza un text cu detalii despre buchet(flori, ornamente, invelitoare, pret)
            var text = "Your bouquet consists of: \n" // variabila in care retinem textul pe care l vom asocia ulterior textView-ului din pagina
            val flowers = arrayOf(buttonRoses, buttonGermini, buttonPeonies, buttonLilies, buttonFreesia, buttonBouvardia,
                                  buttonCarnations, buttonHyancnths, buttonAnthurium, buttonAgapanthus, buttonTullips, buttonLiliac)
            val nrFlowers = arrayOf(nrRoses, nrGermini, nrPeonies, nrLilies, nrFreesia, nrBouvardia, nrCarnations, nrHyancnths,
                                    nrAnthurium, nrAgapanthus, nrTullips, nrLiliac)
            for(i in flowers.indices) {
                if(flowers[i].isSelected) {
                    text = text.plus(flowers[i].contentDescription.split(' ', '(')[0]).plus(": ").plus(nrFlowers[i]).plus('\n')
                    totalCost += (nrFlowers[i] * flowers[i].contentDescription.split(' ', '(')[2].toInt())
                }
            }

            text = text.plus("Ornaments: ")
            val ornaments = arrayOf(buttonOrnament1, buttonOrnament2, buttonOrnament3, buttonOrnament4)
            var selected = false;
            for(i in ornaments.indices){
                if(ornaments[i].isSelected){
                    selected = true;
                    text = text.plus(ornaments[i].contentDescription).plus(", ")
                }
            }
            if(!selected) { // nu a fost selectat nici un ornament
                text = text.plus("None")
            }
            text += ("\n")
            selected = false;
            val wraps = arrayOf(buttonWrap1, buttonWrap2, buttonWrap3, buttonWrap4, buttonWrap5, buttonWrap6)
            text += ("The wrap of the bouquet: ")
            for(i in wraps.indices){
                if(wraps[i].isSelected){
                    selected = true;
                    text = text.plus(wraps[i].contentDescription).plus('\n')
                }

            }
            if(!selected){
                text = text.plus("None")
            }
            text = text.plus("\n").plus("Total cost: ").plus(totalCost).plus(" (de) lei\n")
            textBouquet.text = text

            val bouquetImage : ImageView = findViewById(R.id.bouquetImage)
            bouquetImage.visibility = View.VISIBLE
            /// determina care imagine va fi asociata buchetului generat
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
                searchFlowers(arrayOf(buttonGermini)) -> {
                    bouquetImage.setImageResource(R.drawable.germini_)
                }
                searchFlowers(arrayOf(buttonFreesia)) -> {
                    bouquetImage.setImageResource(R.drawable.freesia_)
                }
                searchFlowers(arrayOf(buttonBouvardia)) -> {
                    bouquetImage.setImageResource(R.drawable.bouvardia_)
                }
                searchFlowers(arrayOf(buttonHyancnths)) -> {
                    bouquetImage.setImageResource(R.drawable.hyancinth_)
                }
                searchFlowers(arrayOf(buttonAnthurium)) -> {
                    bouquetImage.setImageResource(R.drawable.anthurium_)
                }
                searchFlowers(arrayOf(buttonAgapanthus)) -> {
                    bouquetImage.setImageResource(R.drawable.agapanthus_)
                }
                searchFlowers(arrayOf(buttonTullips)) -> {
                    bouquetImage.setImageResource(R.drawable.tullips_)
                }
                searchFlowers(arrayOf(buttonLiliac)) -> {
                    bouquetImage.setImageResource(R.drawable.liliac_)
                }
                searchFlowers(arrayOf(buttonHyancnths, buttonTullips)) -> {
                    bouquetImage.setImageResource(R.drawable.hyancinth_and_tullips)
                }
                searchFlowers(arrayOf(buttonHyancnths, buttonTullips, buttonFreesia)) -> {
                    bouquetImage.setImageResource(R.drawable.hyancinth_tullips_and_freesia)
                }
                searchFlowers(arrayOf(buttonRoses, buttonTullips, buttonLiliac)) -> {
                    bouquetImage.setImageResource(R.drawable.roses_tullips_and_liliac)
                }
                searchFlowers(arrayOf(buttonAnthurium, buttonRoses)) -> {
                    bouquetImage.setImageResource(R.drawable.anthurium_and_roses)
                }
                searchFlowers(arrayOf(buttonRoses, buttonCarnations)) -> {
                    bouquetImage.setImageResource(R.drawable.roses_and_carnations)
                }
                searchFlowers(arrayOf(buttonTullips, buttonPeonies)) -> {
                    bouquetImage.setImageResource(R.drawable.tullips_and_peonies)
                }
                searchFlowers(arrayOf(buttonTullips, buttonPeonies, buttonHyancnths)) -> {
                    bouquetImage.setImageResource(R.drawable.tullips_peonies_and_hyancinth)
                }
                searchFlowers(arrayOf(buttonRoses, buttonPeonies, buttonLilies, buttonTullips)) -> {
                    bouquetImage.setImageResource(R.drawable.roses_peonies_lilies_tullips)
                }
                searchFlowers(arrayOf(buttonRoses, buttonGermini)) -> {
                    bouquetImage.setImageResource(R.drawable.roses_and_germini)
                }
                searchFlowers(arrayOf(buttonRoses, buttonCarnations, buttonGermini)) -> {
                    bouquetImage.setImageResource(R.drawable.roses_carnations_and_germini)
                }
                searchFlowers(arrayOf(buttonRoses, buttonGermini, buttonLiliac)) -> {
                    bouquetImage.setImageResource(R.drawable.roses_germini_and_liliac)
                }

                else -> { // inseamna ca nu exista o imagine pentru cofigurarea respectiva
                    bouquetImage.setImageResource(R.drawable.inexistent_bouquet)
                }
            }

        }
        val buttonRoses: ImageButton = findViewById(R.id.buttonRoses) // buton pentru selectarea trandafirilor sau cresterea numarului de trandafiri doriti
        val buttonCancelRoses : Button = findViewById(R.id.cancelRoses) // buton pentru anularea trandafirilor selectati
            buttonRoses.setOnClickListener{
                val textRoses : TextView = findViewById(R.id.numberRoses)
                if(!buttonSubmit.isSelected){ // daca nu s-a cofirmat alegerea buchetului, putem modifica numarul de trandafiri
                    if(!buttonRoses.isSelected) { /// daca nu a fost selectat inca, se selecteaza, iar numarul acestora este 1
                        nrRoses = 1
                        buttonRoses.isSelected = true // se selecteaza butonul, setand proprietatea pe 'true'
                        buttonRoses.imageAlpha = 100 // 'selectare' vizuala a butonului
                        buttonCancelRoses.visibility = View.VISIBLE // se afiseaza butonul de anulare
                    }
                    else // daca a fost selectat deja, numarul trandafirilor creste cu o unitate
                        nrRoses++

                    textRoses.text = nrRoses.toString() // afisam numarul curent de trandafiri selectati
                }


            }
            buttonCancelRoses.setOnClickListener {
                if(!buttonSubmit.isSelected){ // daca inca se poate modifica buchetul
                    val textRoses : TextView = findViewById(R.id.numberRoses)
                    nrRoses = 0 // setam numarul de trandafiri la 0
                    buttonRoses.imageAlpha = 255 // 'deselectam' imaginea cu trandafiri
                    buttonRoses.isSelected = false // butonul cu trandafiri nu mai este selectat
                    textRoses.text = null // stergem textul care ne indica numarul de trandafiri
                    buttonCancelRoses.visibility = View.INVISIBLE // ascundem butonul de anulare a trandafirilor
                }

            }

            val buttonGermini : ImageButton = findViewById(R.id.buttonGermini) // butonul de selectare a florilor 'Germini'
            val buttonCancelGermini : Button = findViewById(R.id.cancelGermini) // butonul de anulare a florilor 'Germini' din buchet
            buttonGermini.setOnClickListener{
                // acelasi principiu ca la butonul 'buttonroses'
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
                // acelasi principiu ca la butonul 'buttonCancelRoses'
                if(!buttonSubmit.isSelected){
                    val textGermini : TextView = findViewById(R.id.numberGermini)
                    textGermini.text = null
                    nrGermini = 0
                    buttonGermini.imageAlpha = 255
                    buttonGermini.isSelected = false
                    buttonCancelGermini.visibility = View.INVISIBLE
                }
            }
            val buttonPeonies : ImageButton = findViewById(R.id.buttonPeonies)  // buton de selectare a florilor 'Peonies'
            val buttonCancelPeonies : Button = findViewById(R.id.cancelPeonies) // buton de stergere a florilot 'Peonies' din buchet
            buttonPeonies.setOnClickListener{
                // acelasi principiu ca la butonul 'buttonroses'
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
                // acelasi principiu ca la butonul 'buttonCancelRoses'
                if(!buttonSubmit.isSelected){
                    val textPeonies : TextView  = findViewById(R.id.numberPeonies)
                    textPeonies.text = null
                    nrPeonies = 0
                    buttonCancelPeonies.visibility = View.INVISIBLE
                    buttonPeonies.imageAlpha = 255
                    buttonPeonies.isSelected = false
                }
            }
            val buttonLilies : ImageButton = findViewById(R.id.buttonLilies) // buton de selectare a florilor 'Lilies'
            val buttonCancelLilies : Button = findViewById(R.id.cancelLilies) // buton de anulare a florilor 'Lilies' din buchet
            buttonLilies.setOnClickListener {
                // acelasi principiu ca la butonul 'buttonroses'
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
                // acelasi principiu ca la butonul 'buttonCancelRoses'
                if(!buttonSubmit.isSelected){
                    val textLilies : TextView = findViewById(R.id.numberLilies)
                    textLilies.text = null
                    nrLilies = 0
                    buttonLilies.imageAlpha = 255
                    buttonLilies.isSelected = false
                    buttonCancelLilies.visibility = View.INVISIBLE
                }
            }
            val buttonFreesia : ImageButton = findViewById(R.id.buttonFreesia) // buton de selectare a florilor 'Freesia'
            val buttonCancelFreesia : Button = findViewById(R.id.cancelFreesia) // buton de stergere a florilor 'Freesia' din buchet
            buttonFreesia.setOnClickListener {
                // acelasi principiu ca la butonul 'buttonroses'
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
                // acelasi principiu ca la butonul 'buttonCancelRoses'
                if(!buttonSubmit.isSelected){
                    val textFreesia : TextView = findViewById(R.id.numberFreesia)
                    textFreesia.text = null
                    nrFreesia = 0
                    buttonFreesia.imageAlpha = 255
                    buttonFreesia.isSelected = false
                    buttonCancelFreesia.visibility = View.INVISIBLE
                }
            }
            val buttonBouvardia : ImageButton = findViewById(R.id.buttonBouvardia) // buton de selectare a florilor 'Bouvardia'
            val buttonCancelBouvardia : Button = findViewById(R.id.cancelBouvardia) // buton de stergere a florilor 'Bouvardia' din buchet
            buttonBouvardia.setOnClickListener {
                // acelasi principiu ca la butonul 'buttonroses'
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
                // acelasi principiu ca la butonul 'buttonCancelRoses'
                if(!buttonSubmit.isSelected){
                    val textBouvardia : TextView = findViewById(R.id.numberBouvardia)
                    textBouvardia.text = null
                    nrBouvardia = 0
                    buttonBouvardia.imageAlpha = 255
                    buttonBouvardia.isSelected = false
                    buttonCancelBouvardia.visibility = View.INVISIBLE
                }
            }
            val buttonCarnations : ImageButton = findViewById(R.id.buttonCarnations) // buton de adaugare a florilor 'Carnations' in buchet
            val buttonCancelCarnations : Button = findViewById(R.id.cancelCarnations) // buton de stergere a florilor 'Carnations' din buchet
            buttonCarnations.setOnClickListener {
                // acelasi principiu ca la butonul 'buttonroses'
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
                // acelasi principiu ca la butonul 'buttonCancelRoses'
                if(!buttonSubmit.isSelected){
                    val textCarnations : TextView = findViewById(R.id.numberCarnations)
                    textCarnations.text = null
                    nrCarnations = 0
                    buttonCarnations.imageAlpha = 255
                    buttonCarnations.isSelected = false
                    buttonCancelCarnations.visibility = View.INVISIBLE
                }
            }
            val buttonHyancnths : ImageButton = findViewById(R.id.buttonHyancnths) // buton de adaugare a florilor 'Hyancinth' in buchet
            val buttonCancelHyancnths : Button = findViewById(R.id.cancelHyancnths) // buton de stergere 'Hyancinth' din buchet
            buttonHyancnths.setOnClickListener {
                // acelasi principiu ca la butonul 'buttonroses'
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
                // acelasi principiu ca la butonul 'buttonCancelRoses'
                if(!buttonSubmit.isSelected){
                    val textHyancnths : TextView = findViewById(R.id.numberHyancnths)
                    textHyancnths.text = null
                    nrHyancnths = 0
                    buttonHyancnths.imageAlpha = 255
                    buttonHyancnths.isSelected = false
                    buttonCancelHyancnths.visibility = View.INVISIBLE
                }

            }
            val buttonAnthurium : ImageButton = findViewById(R.id.buttonAnthurium) // buton de adaugare a florilor 'Anthurium' in buchet
            val buttonCancelAnthurium : Button = findViewById(R.id.cancelAnthurium) // buton de stergere a florilor 'Anthurium' din buchet
            buttonAnthurium.setOnClickListener {
                // acelasi principiu ca la butonul 'buttonroses'
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
                // acelasi principiu ca la butonul 'buttonCancelRoses'
                if(!buttonSubmit.isSelected){
                    val textAnthurium : TextView = findViewById(R.id.numberAnthurium)
                    textAnthurium.text = null
                    nrAnthurium = 0
                    buttonAnthurium.imageAlpha = 255
                    buttonAnthurium.isSelected = false
                    buttonCancelAnthurium.visibility = View.INVISIBLE
                }

            }
            val buttonAgapanthus : ImageButton = findViewById(R.id.buttonAgapanthus) // buton de selectare 'Agapanthus'
            val buttonCancelAgapanthus : Button = findViewById(R.id.cancelAgapanthus) // buton de anulare flori 'Agapanthus'
            buttonAgapanthus.setOnClickListener {
                // acelasi principiu ca la butonul 'buttonroses'
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
                // acelasi principiu ca la butonul 'buttonCancelRoses'
                if(!buttonSubmit.isSelected){
                    val textAgapanthus : TextView = findViewById(R.id.numberAgapanthus)
                    textAgapanthus.text = null
                    nrAgapanthus = 0
                    buttonAgapanthus.imageAlpha = 255
                    buttonAgapanthus.isSelected = false
                    buttonCancelAgapanthus.visibility = View.INVISIBLE
                }
            }
            val buttonTullips : ImageButton = findViewById(R.id.buttonTullips) // buton de adaugare flori 'Tullips' la buchet
            val buttonCancelTullips : Button = findViewById(R.id.cancelTullips) // buton de stergere 'Tullips' din buchet
            buttonTullips.setOnClickListener {
                // acelasi principiu ca la butonul 'buttonroses'
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
                // acelasi principiu ca la butonul 'buttonCancelRoses'
                if(!buttonSubmit.isSelected){
                    val textTullips : TextView = findViewById(R.id.numberTullips)
                    textTullips.text = null
                    nrTullips = 0
                    buttonTullips.imageAlpha = 255
                    buttonTullips.isSelected = false
                    buttonCancelTullips.visibility = View.INVISIBLE
                }
            }
            val buttonLiliac : ImageButton = findViewById(R.id.buttonLiliac) // buton de adaugare 'Liliac' la buchet
            val buttonCancelLiliac : Button = findViewById(R.id.cancelLiliac) // buton de stergere 'Liliac' din buchet
            buttonLiliac.setOnClickListener {
                // acelasi principiu ca la butonul 'buttonroses'
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
                // acelasi principiu ca la butonul 'buttonCancelRoses'
                if(!buttonSubmit.isSelected){
                    val textLiliac : TextView = findViewById(R.id.numberLiliac)
                    textLiliac.text = null
                    nrLiliac = 0
                    buttonLiliac.imageAlpha = 255
                    buttonLiliac.isSelected = false
                    buttonCancelLiliac.visibility = View.INVISIBLE
                }
            }
            val buttonOrnament1 : ImageButton = findViewById(R.id.buttonOrnament1) // buton de selectare/deselecatre a primului ornament
            buttonOrnament1.setOnClickListener {
                // daca ornamentul a fost deja selectat si se apasa pe el, acesta se deselecteaza, altfel, se selecteaza
                if(!buttonSubmit.isSelected){ // daca inca este permisa configurarea
                    if (buttonOrnament1.isSelected){
                        buttonOrnament1.isSelected = false
                        buttonOrnament1.imageAlpha = 255


                    }else {
                        buttonOrnament1.isSelected = true
                        buttonOrnament1.imageAlpha = 100
                    }
                }
            }

            val buttonOrnament2 : ImageButton = findViewById(R.id.buttonOrnament2) // buton de selecatre/deselectare pentru al doilea ornament
            buttonOrnament2.setOnClickListener {
                // daca ornamentul a fost deja selectat si se apasa pe el, acesta se deselecteaza, altfel, se selecteaza
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

            val buttonOrnament3 : ImageButton = findViewById(R.id.buttonOrnament3) // buton de selectare/deselectare pentru al 3 lea ornament
            buttonOrnament3.setOnClickListener {
                // daca ornamentul a fost deja selectat si se apasa pe el, acesta se deselecteaza, altfel, se selecteaza
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

            val buttonOrnament4 : ImageButton = findViewById(R.id.buttonOrnament4) // buton de selectare/deselctare pentru al 4 lea ornament
            buttonOrnament4.setOnClickListener {
                // daca ornamentul a fost deja selectat si se apasa pe el, acesta se deselecteaza, altfel, se selecteaza
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
            // urmatoarele 6 variabile reprezinta cele 6 tipuri de invelitori
            val buttonWrap1  :ImageButton = findViewById(R.id.buttonWrap1)
            val buttonWrap2  :ImageButton = findViewById(R.id.buttonWrap2)
            val buttonWrap3  :ImageButton = findViewById(R.id.buttonWrap3)
            val buttonWrap4  :ImageButton = findViewById(R.id.buttonWrap4)
            val buttonWrap5  :ImageButton = findViewById(R.id.buttonWrap5)
            val buttonWrap6  :ImageButton = findViewById(R.id.buttonWrap6)
            val wraps = arrayOf(buttonWrap1, buttonWrap2, buttonWrap3, buttonWrap4, buttonWrap5, buttonWrap6) // array care contine toate cele 6 invelitori
                // urmatorul for permite ca maxim o invelitoare sa fie selectata
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


        val buttonReconfigure : Button = findViewById(R.id.buttonConfigureAgain) // buton de reconfigurare a buchetului
        buttonReconfigure.setOnClickListener {
            itemList.clear()
            totalCost = 0
            // se reseteaza butonul de submit
            buttonSubmit.visibility = View.VISIBLE
            buttonSubmit.isSelected = false

            val textBouquet: TextView = findViewById(R.id.textBouquet)
            textBouquet.text = null // se sterge textul cu detaliile despre vechiul buchet

            val bouquetCreated: ImageView = findViewById(R.id.bouquetImage) // se sterge imaginea cu buchetul configurat anterior
            bouquetCreated.visibility = View.INVISIBLE
            bouquetCreated.setImageResource(R.drawable.none_image)

            buttonReconfigure.text = "Press to go up" // daca se apasa din nou pe butonul 'Reconfigure again', acesta deruleaza pagina pana sus
            scrollView2.fullScroll(ScrollView.FOCUS_UP)
        }
    }
}