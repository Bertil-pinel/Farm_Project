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
                $content .= '<a href="/Farm/index.php/product?id=' . $post['name'] . '">' . $post['name'] . '</a>';
                $content .= ' </li>';
            }
            $content .= '</ul>';
        }
        return $content;
    }

    public function getProductHTML($name)
    {
        $content = null;
        if ($this->productCheck->getProductTxt() != null) {
            $product = $this->productCheck->getProduct($name);
            $content = '<h1>' . $product['title'] . '</h1>';
            $content .= '<div class="date">' . $product['date'] . '</div>';
            $content .= '<div class="body">' . $product['body'] . '</div>';
        }
        return $content;
    }


}