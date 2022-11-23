import GameModeCard, {GameModeCardParameters} from "./game-mode-card";

interface GameModeCardRowParameters {
    cards: GameModeCardParameters[]
}

export default function GameModeCardRow(params: GameModeCardRowParameters) {
    return (
        <div className={`flex flex-row justify-center my-6`}>
            {params.cards.map(card =>
                <GameModeCard key={card.title} title={card.title} description={card.description} img={card.img}/>
            )}
        </div>
    );
}
