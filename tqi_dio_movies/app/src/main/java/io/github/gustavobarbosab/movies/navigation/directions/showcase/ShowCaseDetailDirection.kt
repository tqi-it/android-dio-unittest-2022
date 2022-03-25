package io.github.gustavobarbosab.movies.navigation.directions.showcase

import androidx.navigation.NavDirections
import io.github.gustavobarbosab.movies.navigation.arguments.detail.MovieDetailArgument
import io.github.gustavobarbosab.movies.navigation.directions.DirectionAdapter
import io.github.gustavobarbosab.movies.showcase.presentation.ShowCaseFragmentDirections

class ShowCaseDetailDirection() : DirectionAdapter {

    override fun createDirection(): NavDirections {
        val argument = MovieDetailArgument(
            description = "Pil vive nas ruas da cidade medieval de Roc-en-Brume, junto com suas três doninhas mansas." +
                " Ela sobrevive de comida roubada do castelo do sinistro Regente Tristain. " +
                "Um dia, para escapar de seus guardas, Pil se disfarça de princesa. " +
                "Assim, ela embarca em uma aventura louca e delirante, junto com Crobar, " +
                "um grande guarda desajeitado que pensa que ela é uma nobre, e Rigolin," +
                " um jovem bobo maluco. Pil vai ter que salvar Roland, herdeiro legítimo do trono sob a maldição de um feitiço. " +
                "Esta aventura " +
                "vai virar todo o reino de cabeça para baixo e ensinar a Pil que a nobreza pode ser encontrada em todos nós.",
            imageUrl = "https://image.tmdb.org/t/p/original/abPQVYyNfVuGoFUfGVhlNecu0QG.jpg",
            poster = "https://image.tmdb.org/t/p/original/tq3klWQevRK0Or0cGhsw0h3FDWQ.jpg",
            name = "Pil filme",
            id = 676705

        )
        return ShowCaseFragmentDirections.actionDetailFragment(argument)
    }
}