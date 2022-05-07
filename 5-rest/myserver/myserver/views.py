from rest_framework import viewsets

from .serializers import ItemSerializer
from .models import Item

from rest_framework_xml.parsers import XMLParser
from rest_framework_xml.renderers import XMLRenderer


class ItemXMLRenderer(XMLRenderer):
    root_tag_name = 'items'
    item_tag_name = 'item'


class XMLItemViewSet(viewsets.ModelViewSet):
    parser_classes = (XMLParser,)
    renderer_classes = (ItemXMLRenderer,)
    queryset = Item.objects.all()
    serializer_class = ItemSerializer


class JsonItemViewSet(viewsets.ModelViewSet):
    queryset = Item.objects.all()
    serializer_class = ItemSerializer
