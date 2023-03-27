<?php
namespace control;
class Presenter
{
    protected $productCheck;

    public function __construct($productCheck)
    {
        $this->productCheck = $productCheck;
    }

    public function getAllProductsHTML()
    {
        $content = null;
        if ($this->productCheck->getProductsTxt() != null) {
            $content = '<h1>List of Products</h1>  <ul>';
            foreach ($this->productCheck->getProductsTxt() as $post) {
                $content .= ' <li>';
                $content .= '<a href="/TD2/index.php/post?id=' . $post['id'] . '">' . $post['title'] . '</a>';
                $content .= ' </li>';
            }
            $content .= '</ul>';
        }
        return $content;
    }
    

}