package ch.yoyo.tettet
import ch.yoyo.tettet.MainApp.getClass
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.Includes._
import scalafxml.core.{FXMLLoader, FXMLView, NoDependencyResolver}
import javafx.{scene => jfxs}
import scalafx.collections.ObservableBuffer
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.{Alert, ButtonType, ChoiceDialog, TextInputDialog}
import scalafx.scene.input.MouseEvent



object MainApp extends JFXApp {



  val rootResource = getClass.getResource("view/RootLayout.fxml")
  val loader = new FXMLLoader(rootResource, NoDependencyResolver)
  loader.load();
  val roots = loader.getRoot[jfxs.layout.BorderPane]

  stage = new PrimaryStage {
    title = "Game Screen"
    scene = new Scene {
      root = roots
    }
  }


  def showChaos() = {
    val resource = getClass.getResource("view/Chaos.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }



  def showGaia() = {
    val resource = getClass.getResource("view/Gaia.fxml")
    val loader = new FXMLLoader(resource, NoDependencyResolver)
    loader.load();
    val roots = loader.getRoot[jfxs.layout.AnchorPane]
    this.roots.setCenter(roots)
  }

  def showGaiaRules() = {
    val choices = Seq("Gaia", "Main")
    val dialog = new ChoiceDialog(defaultChoice = "Gaia", choices = choices){
      initOwner(stage)
      title = "Gaia Game Mode Rules"
      headerText = "Welcome to Gaia!"
      contentText  = "1. This goal of this game is to get many tetris blocks on the tree branch \n" +
        "2. The tree branch is in the middle, lower part of the entire board \n" +
        "3. Avoid placing the tetris blocks at the red box, otherwise the game will be over \n" +
        "4. Special blocks are tetris blocks that are made of only 2 boxes, this blocks can be placed anywhere, including in the red boxes \n" +
        "5. So keep stacking the leaves, plant more tress on ground using the special blocks, and get as high as you can!"
    }

    val result = dialog.showAndWait()

    result match{
      case Some(name) => if (name == "Gaia"){
        showGaia()
      }
        if (name == "Main"){
          gameStart()
        }
    }
  }

  def showChaosRules() = {
    val choices = Seq("Choas", "Main")
    val dialog = new ChoiceDialog(defaultChoice = "Chaos", choices = choices){
      initOwner(stage)
      title = "Chaos Game Mode Rules"
      headerText = "Welcome to Chaos!"
      contentText  = "1. This goal of this game is to get many points as you can \n" +
        "2. However, be aware of the chaos tetris blocks, they are big and will make your game end faster \n" +
        "3. On the other hand, there is twist, place your blocks wisely and the chaotic blocks won't be a problem \n" +
        "4. More importantly, if you are ever so lucky, a special block that is made of one box only, will appear and this will reset the entire board while keeping the score \n" +
        "5. Hence, pray and strategize your moves wisely, good luck! "
    }

    val result = dialog.showAndWait()

    result match{
      case Some(name) => if (name == "Chaos"){
        showChaos()
      }
        if (name == "Main"){
          gameStart()
        }
    }
  }


  def gameStart(): Unit = {
    val choices = Seq("Gaia", "Chaos", "Gaia Game Rules", "Chaos Game Rules")
    val dialog = new ChoiceDialog(defaultChoice = "Gaia", choices = choices){
      initOwner(stage)
      title = "Main Screen"
      headerText = "Welcome to our game!"
      contentText  = "Please select game mode"
    }

    val result = dialog.showAndWait()


    result match{
      case Some(name) => if (name == "Gaia"){
        showGaia()
      }
        if (name == "Chaos"){
          showChaos()
        }
        if (name == "Gaia Game Rules"){
          showGaiaRules()
        }
        if (name == "Chaos Game Rules"){
          showChaosRules()
        }
    }
  }
  gameStart()
}
