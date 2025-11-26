package com.example.cosmeticsapp

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        val iv = findViewById<ImageView>(R.id.ivDetail)
        val tvName = findViewById<TextView>(R.id.tvDetailName)
        val tvPrice = findViewById<TextView>(R.id.tvDetailPrice)
        val tvDesc = findViewById<TextView>(R.id.tvDetailDesc)
        val btnOrder = findViewById<Button>(R.id.btnOrder)

        val productId = intent.getIntExtra("productId", -1)
        val product = sampleById(productId) ?: run {
            finish()
            return
        }

        iv.setImageResource(product.imageRes)
        tvName.text = product.name
        tvPrice.text = "${String.format("%.2f", product.price)} DH"
        tvDesc.text = product.description

        val animator = ObjectAnimator.ofFloat(tvPrice, "translationX", 100f, 0f)
        val animator1 = ObjectAnimator.ofFloat(tvName, "translationX", 100f, 0f)
        animator.duration = 1500
        animator.interpolator = AccelerateDecelerateInterpolator()
        animator.start()
        animator1.duration = 1500
        animator1.interpolator = AccelerateDecelerateInterpolator()
        animator1.start()

        btnOrder.setOnClickListener {
            Cart.add(product)
            Toast.makeText(this, getString(R.string.added_cart), Toast.LENGTH_SHORT).show()
        }
    }
    private fun sampleById(id: Int): Product? {
        val list = listOf(
            Product(1, "Crème hydratante", 139.99, """
        Crème légère pour peau sèche.
        Hydrate en profondeur tout au long de la journée.
        Formule enrichie en vitamines et agents nourrissants.
        Convient à tous types de peaux.
    """.trimIndent(), R.drawable.cremehydratante),

            Product(2, "Parfum floral", 129.99, """
        Parfum longue durée avec des notes florales délicates.
        Idéal pour les sorties et occasions spéciales.
        Flacon élégant et pratique à transporter.
    """.trimIndent(), R.drawable.parfumfloral),

            Product(3, "Rouge à lèvres", 59.50, """
        Couleur intense et satinée pour un effet glamour.
        Texture douce et facile à appliquer.
        Hydrate les lèvres sans les dessécher.
    """.trimIndent(), R.drawable.rougelvres),

            Product(4, "Shampooing doux", 79.80, """
        Pour cheveux brillants et soyeux.
        Nettoie en douceur sans agresser le cuir chevelu.
        Convient à un usage quotidien.
    """.trimIndent(), R.drawable.shampooingdoux),

            Product(5, "Huile d'argan", 69.90, """
        Riche en vitamine E et antioxydants.
        Nourrit, répare et protège la peau et les cheveux.
        Peut être utilisée pure ou mélangée à vos soins habituels.
    """.trimIndent(), R.drawable.huileargan),

            Product(6, "Masque purifiant", 399.99, """
        Élimine les impuretés et hydrate la peau.
        Formule à base d'argile et d'extraits naturels.
        Laisse la peau douce, nette et revitalisée.
    """.trimIndent(), R.drawable.masquepurifiant),

            Product(7, "Gel douche relaxant", 75.50, """
        Parfum apaisant pour la douche quotidienne.
        Texture douce qui mousse facilement.
        Nettoie en douceur et laisse la peau parfumée.
    """.trimIndent(), R.drawable.geldouche),

            Product(8, "Crème solaire SPF50", 249.00, """
        Protège votre peau des rayons UV nocifs.
        Texture légère, non grasse et rapidement absorbée.
        Convient à tous types de peaux, même sensibles.
    """.trimIndent(), R.drawable.cremesolaire),

            Product(9, "Fond de teint", 129.99, """
        Texture légère pour un fini naturel.
        Couvrance modulable et longue tenue.
        Enrichi en agents hydratants pour un confort optimal.
    """.trimIndent(), R.drawable.fondteint),

            Product(10, "Vernis à ongles", 14.99, """
        Couleur brillante et longue tenue.
        Facile à appliquer avec pinceau précis.
        Séchage rapide et finition parfaite.
    """.trimIndent(), R.drawable.vernis),

            Product(11, "Savon artisanal", 9.99, """
        Fait à la main avec des ingrédients naturels.
        Parfum doux et texture agréable.
        Nettoie et nourrit la peau sans l'assécher.
    """.trimIndent(), R.drawable.savon),

            Product(12, "Baume à lèvres", 119.99, """
        Hydrate et protège les lèvres des agressions extérieures.
        Texture douce et fondante.
        Parfum léger et agréable.
    """.trimIndent(), R.drawable.baumelevres),

            Product(13, "Crème de nuit", 299.00, """
        Régénère et nourrit la peau pendant le sommeil.
        Formule riche en actifs réparateurs.
        Améliore l'élasticité et la fermeté de la peau.
    """.trimIndent(), R.drawable.cremenuit),

            Product(14, "Spray capillaire", 129.50, """
        Fixe et protège vos cheveux toute la journée.
        Laisse une sensation légère et naturelle.
        Convient à tous types de cheveux.
    """.trimIndent(), R.drawable.spraycapillaire),

            Product(15, "Eau micellaire", 140.00, """
        Nettoie et démaquille en douceur.
        Enlève toutes les impuretés sans agresser la peau.
        Adaptée aux peaux sensibles.
    """.trimIndent(), R.drawable.eaubicellaire),

            Product(16, "Lotion tonique", 160.50, """
        Tonifie et rafraîchit la peau après le nettoyage.
        Prépare la peau à recevoir les soins suivants.
        Formule légère et non collante.
    """.trimIndent(), R.drawable.lotiontonique),

            Product(17, "Masque cheveux", 150.99, """
        Répare et nourrit les cheveux secs et abîmés.
        Laisse les cheveux doux, brillants et faciles à coiffer.
        Utilisation hebdomadaire recommandée.
    """.trimIndent(), R.drawable.masquecheveux),

            Product(18, "Crayon yeux", 63.50, """
        Trace un contour précis et longue tenue.
        Texture douce et facile à estomper.
        Résiste à l'eau et aux frottements.
    """.trimIndent(), R.drawable.cranyeux),

            Product(19, "Blush", 60.99, """
        Apporte une touche de couleur naturelle aux joues.
        Texture fine et facile à estomper.
        Tenue longue durée pour un maquillage parfait.
    """.trimIndent(), R.drawable.blush),

            Product(20, "Parfum oriental", 500.99, """
        Senteur envoûtante et mystérieuse.
        Notes chaudes et épicées pour un parfum unique.
        Flacon élégant pour offrir ou se faire plaisir.
    """.trimIndent(), R.drawable.parfumoriental)
        )

        return list.find { it.id == id }
    }
}
