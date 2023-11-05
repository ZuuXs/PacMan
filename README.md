# PacMan
<p><strong>Pacman game using the Java Graphic libraries.</strong></p>
<br/>

## Game
<p>Le projet consiste en la réalisation d’un jeu s’inspirant du jeu Pacman https://fr.wikipedia.org/wiki/
Pac-Man.
Plus précisément, la partie se déroule sur une grille 2D de cases correspondant à un labyrinthe vu de dessus. Le
jeu consiste à déplacer Pacman, un personnage dans un labyrinthe afin de lui faire manger toutes les pacgommes
qui s’y trouvent. Quatre fantômes hantent le labyrinthe et s’y déplacent aléatoirement. Si un des fantômes touche le
pacman alors le pacman perd une des ses trois vies. En plus des pacgommes classique (bleus), il existe aussi quatre
pacgommes bonus (autre couleurs). Ces bonus ont un effet sur le pacman et/ou les fantômes et/ou le labyrinthe.
Le personnage peut emprunter des passages situ´es de chaque côté de l’écran, produisant un effet de wraparound,
le faisant réapparaitre de l’autre côté du labyrinthe. Le tableau suivant indiques les différents points et effet des
pacgommes.</p>
<br/>

## PacGommes
<table>
    <tr>
      <td>Color</td>
      <td>Points</td>
      <td>Effect</td>
    </tr>
    <tr>
      <td>Jaune</td>
      <td>100</td>
      <td>~~</td>
    </tr>
    <tr>
      <td>Violet</td>
      <td>300</td>
      <td>Le pacman devient invisible pour les fantômes. Sa couleur devient jaune pale</td>
    </tr>
    <tr>
      <td>Orange</td>
      <td>500</td>
      <td>Le pacman devient un superpacman sa couleur est alors orange et les fantômes deviennent alors bleus</td>
    </tr>
    <tr>
      <td>Vert</td>
      <td>1000</td>
      <td>Modifie la structure du labyrinthe</td>
    </tr>
</table>
<br/>

## Regles
<ul> 
  <li>Initialement il a trois vies.</li>
  <li>Si le joueur d´epasse les 5000 points, il obtient une vie supplémentaire</li>
  <li>Chaque fantˆome se d´eplace dans une direction jusqu’`a ce qu’il atteigne un mur, puis choisit une nouvelle direction al´eatoirement.</li>
  <li>Quand le pacman est invisible et le pacman pourra traverser les fantˆomes sans perdre de vie.</li>  
  <li>Quand le pacman est un superpacman, les fantˆomes deviennent vuln´erables. Dans ce cas, ils se d´eplacent deux fois plus lentement et ils reviennent au centre du labyrinthe si ils sont touch´es par le pacman.</li>
  <li>Le jeu se termine quand il n’y a plus de pacgommes et la partie est gagn´ee ou quand le pacman a perdu toutes ses vies et la partie est perdue.</li>
</ul>
<br/>

